package main;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Blob {
	private GraphicsContext gc;
	private Physics phys;
	private Color c;
	private Random r = new Random();
	double s = 30;
	
	public Blob(GraphicsContext gc, double x, double y) {
		this.gc = gc;
		c = Color.hsb(r.nextInt(256), 1, 1);
		phys = new Physics(x, y, 3, 0.05);
	}
	
	public void update(double mouseX, double mouseY) {
		phys.seek(mouseX, mouseY);
		phys.updatePosition();
		s *= 0.9999;
	}
	
	public void show() {
		gc.setFill(c);
		gc.fillOval(phys.getX() - s * 0.5, phys.getY() - s * 0.5, s, s);
	}
	
	public boolean eats(Gelantin g) {
		if (phys.distance(g.getPos()) < s / 2 - g.getS() / 2) {
			s++;
			return true;
		}
		return false;
	}
}