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
	int rezX = 800, rezY = 600;
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		root = new Pane();
		scene = new Scene(root,rezX,rezY);
		canvas = new Canvas(rezX,rezY);
		gc = canvas.getGraphicsContext2D();
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		root.getChildren().add(canvas);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		factory = new Factory(gc);
		objects.add(factory.createProduct("hero", x, y));
		// generating the ground for the level
		for(int t=0; t<8; t++)
		{
			double gx = t*100, gy=500;
			
			ground.add(factory.createProduct("groundLow", gx, gy));
		}
		
		
		GameObject main = objects.get(0);
		scene.setOnKeyPressed(event -> {    // should be moved to its own class
			if (event.getCode() == KeyCode.W && !isJumping)
			{
				//isJumping = true;
				main.setY(y -= moveSpeed);
			}
			else if(event.getCode() == KeyCode.S)
			{
				main.setY(y += moveSpeed );
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
				for(GameObject obj:objects)
				{
					obj.update();	
				}
				// drawing the gorund for the level
				for(GameObject gr:ground)
				{
					gr.update();
				}
				
				
				
			
				if(isJumping)
				{
					// Apply gravity to the main object if it is jumping
					velocityY -= gravity;
					y += velocityY;
					
					// If the main object hits the ground, stop the jump not working
					if(y >= canvas.getHeight() - main.getHeight())
					{
						y = canvas.getHeight()-main.getHeight();
						velocityY = 0.0;
						isJumping = false;
					}
					else
					{
						main.setY(y);
					}
				}
				main.setX(x);
			}
			
		};
		
		timer.start();
		
		
		
		
	}

}
