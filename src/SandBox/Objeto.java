package SandBox;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import GameState.Colisiones;

public class Objeto{
	private int ejeX;
	private int ejeY;
	private int altura;
	private int anchura;
	private Colisiones game;

	public Objeto(Colisiones game, int ejeX, int ejeY) {
		setGame(game);
		setEjeX(ejeX);
		setEjeY(ejeY);
		Random r = new Random();
		setAltura(r.nextInt(21)+20);
		setAnchura(r.nextInt(21)+20);
	}

	public void paint(Graphics2D g) {
		g.fillRect(getEjeX(), getEjeY(), getAltura(), getAnchura());
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getEjeX(), getEjeY(), getAltura(), getAnchura());
	}

	/**
	 * @return the ejeX
	 */
	public int getEjeX() {
		return ejeX;
	}

	/**
	 * @param ejeX the ejeX to set
	 */
	public void setEjeX(int ejeX) {
		this.ejeX = ejeX;
	}

	/**
	 * @return the ejeY
	 */
	public int getEjeY() {
		return ejeY;
	}

	/**
	 * @param ejeY the ejeY to set
	 */
	public void setEjeY(int ejeY) {
		this.ejeY = ejeY;
	}
	
	/**
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * @return the anchura
	 */
	public int getAnchura() {
		return anchura;
	}

	/**
	 * @param anchura the anchura to set
	 */
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	public Colisiones getGame() {
		return game;
	}

	public void setGame(Colisiones game) {
		this.game = game;
	}
}
