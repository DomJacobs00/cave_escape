package age491.cave_escape;

import java.util.ArrayList;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CaveEscapeApp extends Application {
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	ArrayList<GameObject> ground = new ArrayList<GameObject>();
	Factory factory;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	
	double x = 0.0, y=0.0;
	double moveSpeed = 5.0;
	double jumpSpeed = 10.0;
	double gravity = 0.5;
	boolean isJumping = false;
	double velocityY = 0.0;
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		root = new Pane();
		scene = new Scene(root, 800,600);
		canvas = new Canvas(800,600);
		gc = canvas.getGraphicsContext2D();
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		root.getChildren().add(canvas);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		factory = new Factory(gc);
		
		// generating the ground for the level
		for(int t=0; t<8; t++)
		{
			double gx = t*100, gy = 500;
			
			ground.add(factory.createProduct("groundLow", gx, gy));
		}
		objects.add(factory.createProduct("hero", x, y));
		GameObject main = objects.get(0);
		scene.setOnKeyPressed(event -> {    // should be moved to its own class
			if (event.getCode() == KeyCode.W && !isJumping)
			{
				;
			}
			else if(event.getCode() == KeyCode.A)
			{
				main.setX(x -= moveSpeed);
			}
			else if(event.getCode() == KeyCode.D)
			{
				main.setX(x += moveSpeed);
			}
		});
		AnimationTimer timer = new AnimationTimer()
		{
			double velocityY = 0.0;
			@Override
			public void handle(long now) {
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
				// drawing the gorund for the level
				for(GameObject gr:ground)
				{
					
					gr.update();
				}
				
				for(GameObject obj:objects)
				{
					obj.update();	
				}
				main.setY(y);
				main.setX(x);
			}
			
		};
		
		timer.start();
		
		
		
		
	}

}
