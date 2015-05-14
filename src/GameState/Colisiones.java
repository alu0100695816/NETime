package GameState;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.Random;

import Main.GamePanel;
import SandBox.Objeto;
import SandBox.Personaje;

public class Colisiones extends GameState{
	
	private boolean primeraVez;
	private Objeto [] objetos;
	private Personaje personaje;
	
	public Colisiones(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		setPersonaje(new Personaje(new Nivel1State(gsm)));
		setPrimeraVez(true);
		Random r = new Random();
		setObjetos(new Objeto[r.nextInt(10)+1]);
		
	}

	@Override
	public void update() {
		getPersonaje().move();
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		if (isPrimeraVez()) {
			creaObjetos();
			setPrimeraVez(false);
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < getObjetos().length; i++) {
			getObjetos()[i].paint(g2d);
		}
		getPersonaje().draw(g2d);
		
	}
	
	public void creaObjetos() {
		Random r = new Random();
		for (int i = 0; i < getObjetos().length; i++) {
			getObjetos()[i] = new Objeto (this, r.nextInt(GamePanel.WIDTH), r.nextInt(GamePanel.HEIGHT));
		}
		
	}

	@Override
	public void keyPressed(int k) {
		if(k != KeyEvent.VK_ESCAPE)
			getPersonaje().keyPressed(k);
		else
			gsm.setCurrentState(GameStateManager.SANDBOX);
	}

	@Override
	public void keyReleased(int k) {
		getPersonaje().keyReleased(k);
		
	}

	public boolean isPrimeraVez() {
		return primeraVez;
	}

	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public Objeto [] getObjetos() {
		return objetos;
	}

	public void setObjetos(Objeto [] objetos) {
		this.objetos = objetos;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

}
