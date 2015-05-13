package SandBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SandBoxFrame extends JFrame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	//private ArrayList<Prueba> pruebas;
	private Prueba prueba1;
	
	public SandBoxFrame() {
		setPrueba1(new Prueba());
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void inicia() throws InterruptedException {
		while(true) {
			getPrueba1().actualiza();
			Thread.sleep(100);
		}
	}

	// Clase interna
	public class ActionTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getPrueba1().actualiza();
			repaint();
		}		
	}
	
	// Getters y setters
	public Prueba getPrueba1() {
		return prueba1;
	}

	public void setPrueba1(Prueba prueba1) {
		this.prueba1 = prueba1;
	}
}

