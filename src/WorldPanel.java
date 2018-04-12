import java.awt.Graphics;
import java.util.ArrayList;

public class WorldPanel implements Drawable {
	private Cell[][] cells;

	int width;
	int height;

	WorldPanel(int width, int height, int cell_size) {
		cells = new Cell[width / cell_size][height / cell_size];

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j] = new Cell(i * 10, j * 10);
			}
		}

		cells[3][5].alive(true);
		cells[3][6].alive(true);
		cells[4][5].alive(true);
		cells[4][6].alive(true);

		cells[7][8].alive(true);

		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		// iterate through cells and draw them

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j].draw(g);
			}
		}
	}

	public void step() {
		// advances world one step
		// iterate through cells and get their neighbors
		// check if each cell should live or die

		ArrayList<Cell> neighbors = new ArrayList<Cell>();

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				neighbors = getNeighbors(i, j);
				cells[i][j].liveOrDie(neighbors);
			}
		}

	}

	public ArrayList<Cell> getNeighbors(int x, int y) {

		ArrayList<Cell> neighbors = new ArrayList<Cell>();

		if (x > 0) {
			neighbors.add(cells[x - 1][y]);
			if (y > 0) {
				neighbors.add(cells[x][y - 1]);
				neighbors.add(cells[x - 1][y - 1]);
			}
			if (y < height) {
				neighbors.add(cells[x][y + 1]);
				neighbors.add(cells[x - 1][y + 1]);
			}
		}
		if (x < width) {
			neighbors.add(cells[x + 1][y]);
			if (y > 0) {
				neighbors.add(cells[x][y - 1]);
				neighbors.add(cells[x + 1][y - 1]);
			}
			if (y < height) {
				neighbors.add(cells[x][y + 1]);
				neighbors.add(cells[x + 1][y + 1]);
			}
		}

		// returns an array list of the 8 or less neighbors of the
		// cell identified by x and y
		return neighbors;
	}

}
