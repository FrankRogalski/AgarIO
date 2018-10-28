package main;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Gelantin {
	private GraphicsContext gc;
	private final Point2D pos;
	private Color c;
	private Random r = new Random();
	private final int s = 10;
	
	public Gelantin(GraphicsContext gc) {
		this.gc = gc;
		
		c = Color.hsb(r.nextInt(256), 1, 1);
		int x = r.nextInt((int) gc.getCanvas().getWidth());
		int y = r.nextInt((int) gc.getCanvas().getHeight());
		pos = new Point2D(x, y);
	}
	
	public void show() {
		gc.setFill(c);
		gc.fillOval(pos.getX() - s * 0.5, pos.getY() - s * 0.5, s, s);
	}

	public Point2D getPos() {
		return pos;
	}

	public int getS() {
		return s;
	}
}