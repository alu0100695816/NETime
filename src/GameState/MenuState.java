package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import TileMap.BackGround;

public class MenuState extends GameState {
	private static final String NOMBRE = "Prueba Juego";
	private static final String BG_PATH = "/Backgrounds/menubg.gif";
	
	private BackGround bg;
	
	private int currentChoice = 0;
	private String[] options = {
		"Start",
		"Help",
		"SandBox",
		"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			
			setBg(new BackGround(BG_PATH, 1));
			getBg().setVector(-0.1, 0);
			
			setTitleColor(new Color(128, 0, 0));
			setTitleFont(new Font(
					"Century Gothic",
					Font.PLAIN,
					28));
			
			setFont(new Font("Arial", Font.PLAIN, 12));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		getBg().update();
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		getBg().draw(g);
		
		// draw title
		g.setColor(getTitleColor());
		g.setFont(getTitleFont());
		g.drawString(NOMBRE, 80, 70);
		
		// draw menu options
		g.setFont(getFont());
		for(int i = 0; i < options.length; i++) {
			if(i == getCurrentChoice()) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
		
	}
	
	private void select() {
		if(getCurrentChoice() == 0) {
			gsm.setCurrentState(GameStateManager.LEVEL1STATE);
		}
		if(getCurrentChoice() == 1) {
			JOptionPane.showMessageDialog(null, "Controles:\nW -> Saltar\nS -> Agacharse\nD -> Derecha\nA -> Izquierda");
		}
		if(getCurrentChoice() == 2) {
			gsm.setCurrentState(GameStateManager.SANDBOX);
			
		}
		if(getCurrentChoice() == 3) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			setCurrentChoice(getCurrentChoice() - 1);
			if(getCurrentChoice() == -1) {
				setCurrentChoice(options.length - 1);
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			setCurrentChoice(getCurrentChoice() + 1);
			if(getCurrentChoice() == options.length) {
				setCurrentChoice(0);
			}
		}
	}
	public void keyReleased(int k) {}

	public BackGround getBg() {
		return bg;
	}

	public void setBg(BackGround bg) {
		this.bg = bg;
	}

	public int getCurrentChoice() {
		return currentChoice;
	}

	public void setCurrentChoice(int currentChoice) {
		this.currentChoice = currentChoice;
	}

	public Color getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}

	public Font getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
}










