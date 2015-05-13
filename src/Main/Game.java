package Main;

import javax.swing.JFrame;

public class Game {
	private static final String NOMBRE = "Prueba Juego";
	
	public static void main(String[] args) {
		JFrame window = new JFrame(NOMBRE);
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
