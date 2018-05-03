import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements ActionListener {
	private Cell[][] cells;

	int width;
	int height;
	int cell_size;

	Timer timer = new Timer(300, this);

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

		// cells[20][10].alive(true);
		// cells[21][10].alive(true);
		// cells[22][10].alive(true);

		cells[15][15].alive(true);
		cells[16][15].alive(true);
		cells[17][15].alive(true);

		cells[18][17].alive(true);
		cells[18][18].alive(true);
		cells[18][19].alive(true);

		cells[15][20].alive(true);
		cells[16][20].alive(true);
		cells[17][20].alive(true);

		cells[13][17].alive(true);
		cells[13][18].alive(true);
		cells[13][19].alive(true);

		cells[20][17].alive(true);
		cells[20][18].alive(true);
		cells[20][19].alive(true);

		cells[21][15].alive(true);
		cells[22][15].alive(true);
		cells[23][15].alive(true);

		cells[25][17].alive(true);
		cells[25][18].alive(true);
		cells[25][19].alive(true);

		cells[21][20].alive(true);
		cells[22][20].alive(true);
		cells[23][20].alive(true);

		cells[15][22].alive(true);
		cells[16][22].alive(true);
		cells[17][22].alive(true);

		cells[13][23].alive(true);
		cells[13][24].alive(true);
		cells[13][25].alive(true);

		cells[15][27].alive(true);
		cells[16][27].alive(true);
		cells[17][27].alive(true);

		cells[18][23].alive(true);
		cells[18][24].alive(true);
		cells[18][25].alive(true);

		this.width = width;
		this.height = height;
		this.cell_size = cell_size;
	}

	@Override
	public void paintComponent(Graphics g) {
		// iterate through cells and draw them
		super.paintComponent(g);

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

		int[][] neighbors = new int[cells.length][cells[0].length];

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				neighbors[i][j] = getNeighbors(i, j);
			}
		}

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j].liveOrDie(neighbors[i][j]);
			}
		}

		repaint();

	}

	public int getNeighbors(int x, int y) {

		int thing = 0;

		if (x > 0) {
			if (cells[x - 1][y].getAlive()) {
				thing++;
			}
		}
		if (x < cells.length - 1) {
			if (cells[x + 1][y].getAlive()) {
				thing++;
			}
		}
		if (y > 0) {
			if (cells[x][y - 1].getAlive()) {
				thing++;
			}
		}
		if (y < cells[0].length - 1) {
			if (cells[x][y + 1].getAlive()) {
				thing++;
			}
		}
		if (x > 0 && y > 0) {
			if (cells[x - 1][y - 1].getAlive()) {
				thing++;
			}
		}
		if (x < cells.length - 1 && y > 0) {
			if (cells[x + 1][y - 1].getAlive()) {
				thing++;
			}
		}
		if (x > 0 && y < cells[0].length - 1) {
			if (cells[x - 1][y + 1].getAlive()) {
				thing++;
			}
		}
		if (x < cells.length - 1 && y < cells[0].length - 1) {
			if (cells[x + 1][y + 1].getAlive()) {
				thing++;
			}
		}

		// returns an array list of the 8 or less neighbors of the
		// cell identified by x and y
		return thing;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}

}
