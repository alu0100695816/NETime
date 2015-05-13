package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {
	// Constantes
	private static final double TWEEN = 0.07;
	private static final int ROWS = 2; 
	private static final String DELIM_REGEX = "\\s+";
	// Variables
	private double x;
	private double y;
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;
	
	private MapTile [][] mapa;
	private int tileSize;
	private int filas;
	private int columnas;

	private int ancho;
	private int alto;
	private BufferedImage tileSet;
	private int numTileAcross;
	private Tile[][] tiles;
	private int rowOffSet;
	private int colOffSet;
	private int filasDibujar;
	private int columnasDibujar;
	
	public TileMap(int tileSize) {
		setTileSize(tileSize);
		setFilasDibujar(GamePanel.HEIGHT / getTileSize() + 2);
		setColumnasDibujar(GamePanel.WIDTH / getTileSize() + 2);
	}
	
	/**
	 * Método para cargar los tiles necesarios
	 * @param s
	 */
	public void cargarTiles(String s) {
		try {
			setTileSet(ImageIO.read(getClass().getResourceAsStream(s)));
			setNumTileAcross(getTileSet().getWidth() / getTileSize());
			setTiles(new Tile[ROWS][numTileAcross]);
			BufferedImage imagenAuxiliar;
			for(int i = 0; i < getNumTileAcross(); i++) {
				imagenAuxiliar = getTileSet().getSubimage(i * getTileSize(), 0, getTileSize(), getTileSize());
				getTiles()[0][i] = new Tile(imagenAuxiliar, Tile.NORMAL);
				imagenAuxiliar = getTileSet().getSubimage(i * getTileSize(), getTileSize(), getTileSize(), getTileSize());
				getTiles()[1][i] = new Tile(imagenAuxiliar, Tile.BLOQUEADO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para cargar el mapa
	 * @param s
	 */
	public void cargarMapa(String s) {
		try {
			InputStream input = getClass().getResourceAsStream(s);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			setColumnas(Integer.parseInt(reader.readLine()));
			setFilas(Integer.parseInt(reader.readLine()));
			setMapa(new MapTile[getFilas()][getColumnas()]);
			setAncho(getColumnas() * getTileSize());
			setAlto(getFilas() * getTileSize());
			
			for(int i = 0; i < getFilas(); i++) {
				String linea = reader.readLine();
				String [] tokens = linea.split(DELIM_REGEX);
				for(int j = 0; j < getColumnas(); j++) {
					setMapItem(i, j, new MapTile(Integer.parseInt(tokens[j])));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		for(int i = getRowOffSet(); i < getRowOffSet() + getFilasDibujar(); i++) {
			if(i >= getFilas())
				break;
			for(int j = getColOffSet(); j < getColOffSet() + getColumnasDibujar(); j++) {
				if(j >= getColumnas())
					break;
				if(getMapa()[i][j].getTileStyle() == 0)
					continue;
				int casilla = getMapa()[i][j].getTileStyle();
				int fila = casilla / getNumTileAcross();
				int columna = casilla % getNumTileAcross();
				int posX = (int)getX() + j * getTileSize();
				int posY = (int)getY() + i * getTileSize();
				getMapa()[i][j].setX(posX);
				getMapa()[i][j].setY(posY);
				
				g.drawImage(getTiles()[fila][columna].getImagen(), posX, posY, null);
			}
		}
	}
	
	// Getters y setters
	public int getType(int i, int j) {
		int casilla = getMapa()[i][j].getTileStyle();
		int fila = casilla / getNumTileAcross();
		int columna = casilla % getNumTileAcross();
		return getTiles()[fila][columna].getTipo();
	}
	
	public void setPosicion(double x, double y) {
		setX((x - getX()) * TWEEN);
		setY((y - getY()) * TWEEN);
		fixBounds();
		
		setColOffSet((int) - getX() / getTileSize());
		setRowOffSet((int) - getY() / getTileSize());
	}
	
	/**
	 * Método que arregla los bordes
	 */
	public void fixBounds() {
		if(getX() < getxMin())
			setX(getxMin());
		if(getY() < getyMin())
			setY(getyMin());
		if(getX() > getxMax())
			setX(getxMax());
		if(getY() > getyMax())
			setY(getyMax());
	}
	
	public void setMapItem(int i, int j, MapTile tile) {
		getMapa()[i][j] = tile;
	}
	
	public BufferedImage getTileSet() {
		return tileSet;
	}

	public void setTileSet(BufferedImage tileSet) {
		this.tileSet = tileSet;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getxMin() {
		return xMin;
	}

	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	public int getyMin() {
		return yMin;
	}

	public void setyMin(int yMin) {
		this.yMin = yMin;
	}

	public int getxMax() {
		return xMax;
	}

	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMax() {
		return yMax;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}
	
	public MapTile[][] getMapa() {
		return mapa;
	}

	public void setMapa(MapTile[][] mapa) {
		this.mapa = mapa;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public int getNumTileAcross() {
		return numTileAcross;
	}

	public void setNumTileAcross(int numTileAcross) {
		this.numTileAcross = numTileAcross;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public int getRowOffSet() {
		return rowOffSet;
	}

	public void setRowOffSet(int rowOffSet) {
		this.rowOffSet = rowOffSet;
	}

	public int getColOffSet() {
		return colOffSet;
	}

	public void setColOffSet(int colOffSet) {
		this.colOffSet = colOffSet;
	}

	public int getFilasDibujar() {
		return filasDibujar;
	}

	public void setFilasDibujar(int filasDibujar) {
		this.filasDibujar = filasDibujar;
	}

	public int getColumnasDibujar() {
		return columnasDibujar;
	}

	public void setColumnasDibujar(int columnasDibujar) {
		this.columnasDibujar = columnasDibujar;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
}
