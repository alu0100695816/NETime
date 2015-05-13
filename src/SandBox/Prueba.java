package SandBox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Prueba extends JPanel implements KeyListener {
	
	private static final int TAMANNO = 20;
	
	private int posX;
	private int posY;
	private int velocidadX;
	private int velocidadY;
	
	public Prueba() {
		setPosX(SandBoxFrame.WIDTH / 2);
		setPosY(SandBoxFrame.HEIGHT / 2);
		setVelocidadX(0);
		setVelocidadY(0);
		addKeyListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		dibujaBola(g);
	}
	
	public void actualiza() {
		setPosX(getPosX() + getVelocidadX());
	}
	
	public void dibujaBola(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		g2.fillOval(getPosX(), getPosY(), TAMANNO, TAMANNO);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("Chivato");
			setVelocidadX(getVelocidadX() + 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			setVelocidadX(getVelocidadX() - 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			setVelocidadX(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	// Getters y setters
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}
}
