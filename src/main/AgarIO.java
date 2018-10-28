package main;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AgarIO extends Application{
	private Canvas can;
	private GraphicsContext gc;
	private Timeline tl_draw;
	
	private Random r = new Random();
	
	private ArrayList<Gelantin> nom = new ArrayList<Gelantin>();
	private Blob blob;
	private double mouseX = 0, mouseY = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		tl_draw = new Timeline(new KeyFrame(Duration.millis(1000.0 / 60.0), e -> {
			draw();
		}));
		tl_draw.setCycleCount(Timeline.INDEFINITE);
		tl_draw.play();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 800, 800);
		
		stage.setTitle("Agar.io");
		
		can = new Canvas(scene.getWidth(), scene.getHeight());
		gc = can.getGraphicsContext2D();
		
		root.getChildren().add(can);
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		stage.setScene(scene);
		stage.show();
		
		//setup
		for (int i = 0; i < 200; i++) {
			nom.add(new Gelantin(gc));
		}
		
		blob = new Blob(gc, can.getWidth() * 0.5, can.getHeight() * 0.5);
	}
	
	private void draw() {
		gc.clearRect(0, 0, can.getWidth(), can.getHeight());
		if (r.nextDouble() < 0.005) {
			nom.add(new Gelantin(gc));
		}
		for (int i = nom.size() - 1; i >= 0; i--) {
			Gelantin g = nom.get(i);
			if (blob.eats(g)) {
				nom.remove(i);
			} else {
				g.show();
			}
		}
		
		blob.update(mouseX, mouseY);
		blob.show();
	}
	
	public final static double map(double value, double min, double max, double nMin, double nMax) {
		return ((value - min) / (max - min)) * (nMax - nMin) + nMin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}