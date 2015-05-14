package SandBox;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import GameState.Nivel1State;
import TileMap.MapTile;

public class Personaje {
	private static final int ARRIBA = 0;
	private static final int ABAJO = 1;
	private static final int IZQUIERDA = 2;
	private static final int DERECHA = 3;
	private static final int VELOCIDAD_X = 3;
	private static final int REBOTE = 1;
	private static final int NLADOS = 4;				// Número de lados del rectangulo, para realizar la comprobación de las colisiones
	private Nivel1State game;
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
	
	public Personaje(Nivel1State game) {
		setContadorSalto(0);
		setAceleracionY(ACELERACION_SALTO);
		setStand(true);
		setTamanno(20);
		setVelocidadX(0);
		setVelocidadY(0);
		setGame(game);
		setPosX(100);
		setPosY(100);
	}

	/**
	 * Método que realiza el movimiento del personaje teniendo en cuenta las colisiones
	 */
	public void move() {
		/*ArrayList<MapTile> arriba = new ArrayList<MapTile>();
		ArrayList<MapTile> abajo = new ArrayList<MapTile>();
		ArrayList<MapTile> izquierda = new ArrayList<MapTile>();
		ArrayList<MapTile> derecha = new ArrayList<MapTile>();
		Rectangle aux;
		MapTile [][] objetos = getGame().getTileMap().getMapa();
		for (int i = 0; i < objetos.length; i++) {
			for(int j = 0; j < objetos[0].length; j++) {
				if (objetos[i] != null) {
					if(objetos[i][j].getType() == MapTile.BLOQUEADO) {
						aux = objetos[i][j].getBounds();
						if (getPosX() < (int) Math.floor(aux.getX())) {
							derecha.add(objetos[i][j]);
						}
						else {
							izquierda.add(objetos[i][j]);
						}
						if (getPosY() < (int)Math.floor(aux.getY())){
							abajo.add(objetos[i][j]);
						}
						else{
							arriba.add(objetos[i][j]);
						}
					}
				}
			}
		}
		
		
		*/
		movimiento();
	}
	
	public boolean colisionRealizada() {
		MapTile [][] objetos = getGame().getTileMap().getMapa();
		for (int i = 0; i < objetos.length; i++) {
			for(int j = 0; j < objetos[0].length; j++) {
				if (objetos[i] != null) {
					if(objetos[i][j].getType() == MapTile.BLOQUEADO) {
						if(getBounds().intersects(objetos[i][j].getBounds())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean colisionLateral() {
		MapTile [][] objetos = getGame().getTileMap().getMapa();
		Rectangle [] personaje = new Rectangle[NLADOS];
		Rectangle cuadradoPersonaje = getBounds();
		int x = (int) Math.floor(cuadradoPersonaje.getX());
		int y = (int) Math.floor(cuadradoPersonaje.getY());
		int altura = (int) Math.floor(cuadradoPersonaje.getHeight());
		int anchura = (int) Math.floor(cuadradoPersonaje.getWidth());
		personaje[0] = new Rectangle (x, y, anchura, altura);
		personaje[1] = new Rectangle (x, altura / 2, anchura, altura / 2);
		personaje[2] = new Rectangle (x, y, anchura / 2, altura);
		personaje[3] = new Rectangle (anchura / 2, y, anchura / 2, altura);
		for (int i = 0; i < objetos.length; i++) {
			for(int j = 0; j < objetos[0].length; j++) {
				if (objetos[i] != null) {
					if(objetos[i][j].getType() == MapTile.BLOQUEADO) {
						if(personaje[IZQUIERDA].intersects(objetos[i][j].getBounds()))
							return true;
						else if(personaje[DERECHA].intersects(objetos[i][j].getBounds()))
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public void movimiento() {
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
			setVelocidadX(VELOCIDAD_X);
		if(isLeft())
			setVelocidadX(-VELOCIDAD_X);
		setPosX(getPosX() + getVelocidadX());
		setPosY(getPosY() + getVelocidadY());
		if(!colisionRealizada()) {
			if(getVelocidadY() < ACELERACION_SALTO / 2)
				setVelocidadY((int)Math.floor((double)getVelocidadY() + GRAVEDAD));
		}
		else {
			setContadorSalto(0);
			setSalto(false);
			setDobleSalto(false);
			setVelocidadY(0);
		}
		if(colisionLateral()) {
			setVelocidadX(0);
		}
	}

	/**
	 * Método que pinta el personaje
	 * @param g donde pinta el personaje
	 */
	public void draw(Graphics2D g) {
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g.fillOval(getPosX(), getPosY(), getTamanno(), getTamanno());
	}

	/**
	 * Método que captura cuando el jugador suelta la tecla que estaba pulsando
	 * @param e tecla dejada de pulsar
	 */
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT)
			setLeft(false);
		if(k == KeyEvent.VK_RIGHT)
			setRight(false);
		if(k == KeyEvent.VK_DOWN) {
			setAceleracionY(ACELERACION_SALTO);
			setStand(true);
			setPosY(getPosY() - 10);
		}
		
		setVelocidadX(0);
	}

	/**
	 * Método que captura las teclas presionadas por el usuario
	 * @param e
	 */
	public void keyPressed(int k) {
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

	/**
	 * Método que devuelve el cuadrado que contiene al personaje
	 * @return cuadrado que contiene al cuadrado
	 */
	public Rectangle getBounds() {
		return new Rectangle(getPosX(), getPosY(), getTamanno(), getTamanno());
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
		Rectangle arriba = new Rectangle (x, y, anchura, altura);
		Rectangle abajo = new Rectangle (x, y + altura - 1, anchura, 1);
		Rectangle izquierda = new Rectangle (x, y, 1, altura);
		Rectangle derecha = new Rectangle (x + anchura - 1, y, 1, altura);
		colisiones[0] = abajo.intersects(personaje[0]);
		colisiones[1] = arriba.intersects(personaje[1]);
		colisiones[2] = derecha.intersects(personaje[2]);
		colisiones[3] = izquierda.intersects(personaje[3]);
		return colisiones;
	}


	// Gettesr y setters
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