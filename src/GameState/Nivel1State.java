package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import SandBox.Personaje;
import TileMap.BackGround;
import TileMap.TileMap;

public class Nivel1State extends GameState {
	private static final int TILE_SIZE = 30;
	private static final String TILE_PATH = "/Tilesets/grasstileset.gif";
	private static final String MAP_PATH = "/Maps/level1-1.map";
	private static final String MAP_BACK = "/Backgrounds/backTenerife.jpg";
	
	private TileMap mapa;
	private BackGround bg;
	private Personaje pj;
	
	
	public Nivel1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init() {
		setMapa(new TileMap(TILE_SIZE));
		getTileMap().cargarTiles(TILE_PATH);
		getTileMap().cargarMapa(MAP_PATH);
		getTileMap().setPosicion(0, 0);
		
		bg = new BackGround(MAP_BACK, 0.1);
		
		pj = new Personaje(this);
	}

	@Override
	public void update() {
		getPj().move();
	}

	@Override
	public void draw(Graphics2D g) {
		
		//Dibujar Background
		bg.draw(g);
		
		//Dibujar Mapa
		getTileMap().draw(g);
		
		//Dibujar personaje
		pj.paint(g);
		
	}

	@Override
	public void keyPressed(int k) {
		if(k != KeyEvent.VK_ESCAPE)
			getPj().keyPressed(k);
		else
			gsm.setCurrentState(GameStateManager.MENUSTATE);
	}

	@Override
	public void keyReleased(int k) {
		getPj().keyReleased(k);
	}

	public TileMap getTileMap() {
		return mapa;
	}

	public void setMapa(TileMap mapa) {
		this.mapa = mapa;
	}

	/**
	 * @return the pj
	 */
	public Personaje getPj() {
		return pj;
	}

	/**
	 * @param pj the pj to set
	 */
	public void setPj(Personaje pj) {
		this.pj = pj;
	}

}
