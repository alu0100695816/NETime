package GameState;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int SANDBOX = 2;
	public static final int SALTO = 3;
	public static final int COLISIONES = 4;
	
	public GameStateManager() {
		
		setGameStates(new ArrayList<GameState>());
		
		setCurrentState(MENUSTATE);
		getGameStates().add(new MenuState(this));
		getGameStates().add(new Nivel1State(this));	
		getGameStates().add(new SandBoxState(this));
		getGameStates().add(new Salto(this));
		getGameStates().add(new Colisiones(this));
		
		
	}
	
	public void setState(int state) {
		setCurrentState(state);
		getGameStates().get(getCurrentState()).init();
	}
	
	public void update() {
		getGameStates().get(getCurrentState()).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		getGameStates().get(getCurrentState()).draw(g);
	}
	
	public void keyPressed(int k) {
		getGameStates().get(getCurrentState()).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		getGameStates().get(getCurrentState()).keyReleased(k);
	}

	public ArrayList<GameState> getGameStates() {
		return gameStates;
	}

	public void setGameStates(ArrayList<GameState> gameStates) {
		this.gameStates = gameStates;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}
	
}









