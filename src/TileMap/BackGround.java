package TileMap;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class BackGround {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public BackGround(String s, double ms) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			setMoveScale(ms);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.setX((x * getMoveScale()) % GamePanel.WIDTH);
		this.setY((y * getMoveScale()) % GamePanel.HEIGHT);
	}
	
	public void setVector(double dx, double dy) {
		this.setDx(dx);
		this.setDy(dy);
	}
	
	public void update() {
		setX(getX() + getDx());
		setY(getY() + getDy());
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)getX(), (int)getY(), null);
		
		if(getX() < 0) {
			g.drawImage(
				image,
				(int)getX() + GamePanel.WIDTH,
				(int)getY(),
				null
			);
		}
		if(getX() > 0) {
			g.drawImage(
				image,
				(int)getX() - GamePanel.WIDTH,
				(int)getY(),
				null
			);
		}
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

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getMoveScale() {
		return moveScale;
	}

	public void setMoveScale(double moveScale) {
		this.moveScale = moveScale;
	}
	
}







