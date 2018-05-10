import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConwaysGameOfLife extends JPanel implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static final int CELL_SIZE = 10;

	private JFrame window;
	private JPanel panel;
	private JButton startStopButton;
	private JButton clearButton;
	private JTextField speedField;

	WorldPanel wp = new WorldPanel(WIDTH, HEIGHT, CELL_SIZE);

	ConwaysGameOfLife() {

		window = new JFrame();
		startStopButton = new JButton();
		speedField = new JTextField(3);
		panel = new JPanel();
		clearButton = new JButton();

	}

	public static void main(String[] args) {

		ConwaysGameOfLife c = new ConwaysGameOfLife();
		c.launchGame();

	}

	public void runSimulation() {
		wp.step();
	}

	public void launchGame() {
		// build the window and start the simulation

		window.add(this);
		window.add(wp);
		window.addMouseListener(wp);
		wp.add(panel);
		window.setVisible(true);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.pack();
		startStopButton.setSize(50, 20);
		startStopButton.setText("Start/Stop");
		panel.add(startStopButton);
		panel.add(clearButton);
		panel.add(speedField);
		speedField.setText("300");
		clearButton.setText("Clear");
		clearButton.addActionListener(this);
		startStopButton.addActionListener(this);
		wp.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// runSimulation();
		if (arg0.getSource() == startStopButton) {
			wp.timer.setDelay(Integer.parseInt(speedField.getText()));
			if (wp.timer.isRunning()) {
				wp.timer.stop();
			} else {
				wp.timer.start();
			}
		}
		if (arg0.getSource() == clearButton) {
			wp.clear();
		}
	}

}
