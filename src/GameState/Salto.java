package GameState;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import Main.GamePanel;

public class Salto extends GameState{
	private static double GRAVEDAD = 2;
	private static int ACELERACION_SALTO = 15;
	
//	private BufferedImage jugador;
	private int posX;
	private int posY;
	private int velocidadX;
	private int velocidadY;
	private int aceleracionY;
	
	private int contadorSalto;

	private boolean right;
	private boolean left;
	private boolean salto;
	private boolean dobleSalto;

	private boolean stand;
	
	private int tamanno;

	public Salto(GameStateManager gsm) {
		setContadorSalto(0);
		setAceleracionY(ACELERACION_SALTO);
		setStand(true);
		setTamanno(20);
		this.gsm = gsm;
		setPosX(GamePanel.WIDTH / 2);
		setPosY(GamePanel.HEIGHT - ACELERACION_SALTO * 2);
		setVelocidadX(0);
		setVelocidadY(0);
	}

	@Override
	public void init() {}

	@Override
	public void update() {
		if(isSalto() && getContadorSalto() == 0) {
			setVelocidadY(getVelocidadY() - getAceleracionY());
			setContadorSalto(1);
		}
		if(isDobleSalto() && getContadorSalto() == 1) {
			setVelocidadY(getVelocidadY() - (getAceleracionY()));
			setContadorSalto(2);
			setSalto(false);
			setDobleSalto(false);
		}
		if(isStand())
			setTamanno(20);
		else
			setTamanno(10);
		if(isRight())
			setVelocidadX(3);
		if(isLeft())
			setVelocidadX(-3);
		setPosX(getPosX() + getVelocidadX());
		setPosY(getPosY() + getVelocidadY());
		if(getPosY() < GamePanel.HEIGHT - ACELERACION_SALTO * 2) {
			if(getVelocidadY() < ACELERACION_SALTO / 2)
				setVelocidadY((int)Math.floor(getVelocidadY() + GRAVEDAD));
		}
		else {
			setContadorSalto(0);
			setPosY(GamePanel.HEIGHT - ACELERACION_SALTO * 2);
			setSalto(false);
			setDobleSalto(false);
			setVelocidadY(0);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g.fillOval(getPosX(), getPosY(), getTamanno(), getTamanno());
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE)
			gsm.setCurrentState(GameStateManager.SANDBOX);
		if(k == KeyEvent.VK_LEFT)
			setLeft(true);
		if(k == KeyEvent.VK_RIGHT)
			setRight(true);
		if(k == KeyEvent.VK_UP) {
			if(isSalto())
				setDobleSalto(true); 
			else
				setSalto(true);
		}
		if(k == KeyEvent.VK_DOWN) {
			setAceleracionY(ACELERACION_SALTO / 2);
			setStand(false);
		}
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT)
			setLeft(false);
		if(k == KeyEvent.VK_RIGHT)
			setRight(false);
		if(k == KeyEvent.VK_DOWN) {
			setAceleracionY(ACELERACION_SALTO);
			setStand(true);
		}
		
		setVelocidadX(0);
	}

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

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public int getTamanno() {
		return tamanno;
	}

	public void setTamanno(int tamanno) {
		this.tamanno = tamanno;
	}

	public boolean isStand() {
		return stand;
	}

	public void setStand(boolean stand) {
		this.stand = stand;
	}

	public int getAceleracionY() {
		return aceleracionY;
	}

	public void setAceleracionY(int aceleracionY) {
		this.aceleracionY = aceleracionY;
	}

	public boolean isSalto() {
		return salto;
	}

	public void setSalto(boolean salto) {
		this.salto = salto;
	}

	public boolean isDobleSalto() {
		return dobleSalto;
	}

	public void setDobleSalto(boolean dobleSalto) {
		this.dobleSalto = dobleSalto;
	}

	public int getContadorSalto() {
		return contadorSalto;
	}

	public void setContadorSalto(int contadorSalto) {
		this.contadorSalto = contadorSalto;
	}
}
