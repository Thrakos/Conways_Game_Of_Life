import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cell implements Drawable {
	private int x;
	private int y;
	private boolean isAlive;

	Cell(int x, int y) {

		this.x = x;
		this.y = y;

	}

	public void liveOrDie(ArrayList<Cell> neighbors) {
		// sets isAlive to true or false based on the neighbors and
		// the rules of the game
		/*
		 * 1. Any live cell with fewer than two live neighbours dies, as if caused by
		 * underpopulation. 2. Any live cell with two or three live neighbours lives on
		 * to the next generation. 3. Any live cell with more than three live neighbours
		 * dies, as if by overpopulation. 4. Any dead cell with exactly three live
		 * neighbours becomes a live cell, as if by reproduction. (source: Wikipedia)
		 */

		int liveNeighbors = 0;

		for (Cell c : neighbors) {
			if (c.isAlive) {
				liveNeighbors++;
			}
		}

		if (isAlive) {
			if (liveNeighbors < 2) {
				isAlive = false;
			} else if (liveNeighbors > 3) {
				isAlive = false;
			}
		} else if (liveNeighbors == 3) {
			isAlive = true;
		}

	}

	public void alive(boolean thing) {
		isAlive = thing;
	}

	@Override
	public void draw(Graphics g) {
		// draws colored square if cell is alive
		// draws empty square if cell is dead

		g.setColor(Color.BLACK);

		if (isAlive) {
			g.fillRect(x, y, ConwaysGameOfLife.CELL_SIZE, ConwaysGameOfLife.CELL_SIZE);
		} else {
			g.drawRect(x, y, ConwaysGameOfLife.CELL_SIZE, ConwaysGameOfLife.CELL_SIZE);
		}

	}

}
