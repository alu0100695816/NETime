package TileMap;

import java.awt.Rectangle;

public class MapTile {

	private static final int TILE_SIZE = 30;
	public static final int NORMAL = 0;
	public static final int BLOQUEADO = 1;
	public static final int CORTE = 20;
	
	private int x;
	private int y;
	private int tileStyle;
	private int type;
	
	public MapTile(int tileStyle) {
		setTileStyle(tileStyle);
		if(tileStyle < CORTE) {
			type = NORMAL;
		}
		else {
			type = BLOQUEADO;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), TILE_SIZE, TILE_SIZE);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the tileStyle
	 */
	public int getTileStyle() {
		return tileStyle;
	}

	/**
	 * @param tileStyle the tileStyle to set
	 */
	public void setTileStyle(int tileStyle) {
		this.tileStyle = tileStyle;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
