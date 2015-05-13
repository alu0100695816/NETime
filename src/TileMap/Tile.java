/**
 * Clase Tile que se encarga de almacenar la imagen de cada uno de los bloques y asignarles un tipo
 * Lo hablado de atravesable o no.
 */
package TileMap;

import java.awt.image.BufferedImage;

public class Tile {
	// Tipos de tile
	public static final int NORMAL = 0;
	public static final int BLOQUEADO = 1;
	
	// Imagen del tile y tipo de tile
	private BufferedImage imagen;
	private int tipo;
	
	public Tile(BufferedImage imagen, int tipo) {
		setImagen(imagen);
		setTipo(tipo);
	}
	
	// Getters y setters
	public BufferedImage getImagen() {
		return imagen;
	}
	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
