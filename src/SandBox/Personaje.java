package SandBox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import GameState.Colisiones;
import GameState.Nivel1State;
import Main.GamePanel;
import TileMap.MapTile;

public class Personaje {
	private static final int ACELERACION = 1;			// Aceleración del personaje
	private static final int NLADOS = 4;				// Número de lados del rectangulo, para realizar la comprobación de las colisiones
	private static final int WITH = 10;					// Ancho del personaje
	private static final int HEIGHT = 20;				// Altura del personaje
	private int ejeX;									// Coordenada X donde se encuentra el personaje
	private int aceleracionX = 0;						// Aceleración actual del personaje en el eje X
	private int ejeY;									// Coordenada Y donde se encuentra el personaje
	private int  aceleracionY = 0;						// Aceleración actual del personaje en el eje Y
	private Colisiones gameLevel;							// Donde se realiza el juego
	private Nivel1State game;
	
	public Personaje(Nivel1State game) {
		setGame(game);
		setEjeX(100);
		setEjeY(100);
	}

	/**
	 * Método que realiza el movimiento del personaje teniendo en cuenta las colisiones
	 */
	public void move() {
		boolean [] colisiones = new boolean [NLADOS];
		Rectangle [] personaje = new Rectangle[NLADOS];
		MapTile [][] objetos = getGame().getTileMap().getMapa();
		if (objetos.length > 0) {
			Rectangle cuadradoPersonaje = getBounds();
			int x = (int) Math.floor(cuadradoPersonaje.getX());
			int y = (int) Math.floor(cuadradoPersonaje.getY());
			int altura = (int) Math.floor(cuadradoPersonaje.getHeight());
			int anchura = (int) Math.floor(cuadradoPersonaje.getWidth());
			/*
			 * personaje[0] --> cuadrado de arriba
			 * personaje[1] --> cuadrado de abajo
			 * personaje[2] --> cuadrado de la izquierda
			 * personaje[3] --> cuadrado de la derecha
			*/
			personaje[0] = new Rectangle (x, y, anchura, 1);
			personaje[1] = new Rectangle (x, y + altura - 1, anchura, 1);
			personaje[2] = new Rectangle (x, y, 1, altura);
			personaje[3] = new Rectangle (x + anchura - 1, y, 1, altura);
			for (int i = 0; i < objetos.length; i++) {
				for(int j = 0; j < objetos[0].length; j++) {
					if (objetos[i] != null) {
						if(objetos[i][j].getType() == MapTile.BLOQUEADO) {
							Rectangle cuadradoObjeto = objetos[i][j].getBounds();
							colisiones = colision(cuadradoObjeto, personaje);
							if (colisiones[0] == true) {
								System.out.println("Ha chocado por arriba");
								setEjeY(getEjeY() + ACELERACION);
							}

							if (colisiones[1] == true) {
								System.out.println("Ha chocado por abajo");
								setEjeY(getEjeY() - ACELERACION);
							}

							if (colisiones[2] == true) {
								System.out.println("Ha chocado por la izquierda");
								setEjeX(getEjeX() + ACELERACION);
							}

							if (colisiones[3] == true) {
								System.out.println("Ha chocado por la derecha");
								setEjeX(getEjeX() - ACELERACION);
							}
						}
					}
				}
			}
		}
		// Hacemos el movimiento comprobando solo los margenes, ya que las colisiones ya las tenemos comprobadas
		if (getEjeX() + getAceleracionX() > 0 && getEjeX() + getAceleracionX() < GamePanel.WIDTH - WITH)
			setEjeX(getEjeX() + getAceleracionX());

		if (getEjeY() + getAceleracionY() > 0 && getEjeY() + getAceleracionY() < GamePanel.HEIGHT - HEIGHT)
			setEjeY(getEjeY() + getAceleracionY());
		
	}

	/**
	 * Método que pinta el personaje
	 * @param g donde pinta el personaje
	 */
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(getEjeX(), getEjeY(), WITH, HEIGHT);
	}

	/**
	 * Método que captura cuando el jugador suelta la tecla que estaba pulsando
	 * @param e tecla dejada de pulsar
	 */
	public void keyReleased(int e) {
		if (e == KeyEvent.VK_LEFT || e == KeyEvent.VK_RIGHT)
			setAceleracionX(0);
		if (e == KeyEvent.VK_UP || e == KeyEvent.VK_DOWN)
			setAceleracionY(0);
	}

	/**
	 * Método que captura las teclas presionadas por el usuario
	 * @param e
	 */
	public void keyPressed(int e) {
		if (e == KeyEvent.VK_LEFT)
			setAceleracionX(-ACELERACION);
		if (e == KeyEvent.VK_RIGHT)
			setAceleracionX(ACELERACION);
		if (e == KeyEvent.VK_UP)
			setAceleracionY(-ACELERACION);
		if (e == KeyEvent.VK_DOWN)
			setAceleracionY(ACELERACION);
	}

	/**
	 * Método que devuelve el cuadrado que contiene al personaje
	 * @return cuadrado que contiene al cuadrado
	 */
	public Rectangle getBounds() {
		return new Rectangle(getEjeX(), getEjeY(), WITH, HEIGHT);
	}

	/**
	 * Método que calcula las colisiones entre un objeto y el personaje
	 * @param cuadradoObjeto cudrado que contiene al objeto
	 * @param personaje cuadrado con los extremos del cuadrado del personaje
	 * personaje[0] --> cuadrado de arriba
	 * personaje[1] --> cuadrado de abajo
	 * personaje[2] --> cuadrado de la izquierda
	 * personaje[3] --> cuadrado de la derecha
	 * @return devuelve si hay colisión o no en cada uno de los rectangulos de los personajes, por lo que el vector tiene la misma forma
	 */
	private boolean [] colision (Rectangle cuadradoObjeto, Rectangle [] personaje) {
		boolean [] colisiones = new boolean [NLADOS];
		int x = (int) Math.floor(cuadradoObjeto.getX());
		int y = (int) Math.floor(cuadradoObjeto.getY());
		int altura = (int) Math.floor(cuadradoObjeto.getHeight());
		int anchura = (int) Math.floor(cuadradoObjeto.getWidth());
		Rectangle arriba = new Rectangle (x, y, anchura, 1);
		Rectangle abajo = new Rectangle (x, y + altura - 1, anchura, 1);
		Rectangle izquierda = new Rectangle (x, y, 1, altura);
		Rectangle derecha = new Rectangle (x + anchura - 1, y, 1, altura);
		colisiones[0] = abajo.intersects(personaje[0]);
		colisiones[1] = arriba.intersects(personaje[1]);
		colisiones[2] = derecha.intersects(personaje[2]);
		colisiones[3] = izquierda.intersects(personaje[3]);
		return colisiones;
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
	 * @return the aceleracionX
	 */
	public int getAceleracionX() {
		return aceleracionX;
	}

	/**
	 * @param aceleracionX the aceleracionX to set
	 */
	public void setAceleracionX(int aceleracionX) {
		this.aceleracionX = aceleracionX;
	}

	/**
	 * @return the EjeY
	 */
	public int getEjeY() {
		return ejeY;
	}

	/**
	 * @param EjeY the EjeY to set
	 */
	public void setEjeY(int EjeY) {
		this.ejeY = EjeY;
	}

	/**
	 * @return the aceleracionY
	 */
	public int getAceleracionY() {
		return aceleracionY;
	}

	/**
	 * @param aceleracionY the aceleracionY to set
	 */
	public void setAceleracionY(int aceleracionY) {
		this.aceleracionY = aceleracionY;
	}

	public Nivel1State getGame() {
		return game;
	}

	public void setGame(Nivel1State game) {
		this.game = game;
	}
	
	/*public Colisiones getGame() {
		return game;
	}

	public void setGame(Colisiones game) {
		this.game = game;
	}*/
}