import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConwaysGameOfLife extends JPanel implements ActionListener {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int CELL_SIZE = 10;

	private JFrame window;
	private JButton startStopButton;
	private JTextField speedField;

	WorldPanel wp = new WorldPanel(WIDTH, HEIGHT, CELL_SIZE);

	ConwaysGameOfLife() {

		window = new JFrame();
		startStopButton = new JButton();
		speedField = new JTextField();

	}

	public static void main(String[] args) {

		ConwaysGameOfLife c = new ConwaysGameOfLife();
		c.launchGame();

	}

	public void runSimulation() {
		while (true) {
			wp.step();
		}
	}

	public void launchGame() {
		// build the window and start the simulation

		window.add(this);
		window.setVisible(true);
		window.setSize(WIDTH, HEIGHT);
		startStopButton.setSize(50, 20);
		startStopButton.setText("Start/Stop");
		this.add(startStopButton);
		startStopButton.addActionListener(this);
		repaint();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		runSimulation();
	}

	@Override
	public void paintComponent(Graphics g) {
		wp.draw(g);
	}
}
