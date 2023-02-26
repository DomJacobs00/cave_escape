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
	ArrayList<GameObject> objects = new ArrayList<GameObject>(); // stores all the characters ( main character and enemies)
	ArrayList<GameObject> ground = new ArrayList<GameObject>(); // stores all objects that create the ground
	Factory factory;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	
	double x = 0.0, y=0.0;
	double moveSpeed = 5.0;
	double jumpSpeed = 10.0;
	double gravity = 0.7;
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
		
		/**
		 * generating the ground for the level
		 * the ground consists of GroundGenerator objects that are used as 100x100 tiles to be placed on the ground level.
		 * Later the tiles will be randomly generated to make the terrain of the level more different
		 */
		for(int t=0; t<8; t++)
		{
			double gx = t*100, gy = 500;
			
			ground.add(factory.createProduct("groundLow", gx, gy));
		}
		// Addition of a controllable character hero
		objects.add(factory.createProduct("hero", x, y));
		
		GameObject main = objects.get(0); // Accessing the character form the list of objects
		
		// movement for the character ( will be moved to separate class maybe?)
		scene.setOnKeyPressed(event -> {    
			if (event.getCode() == KeyCode.W && !isJumping) // w is binded to make the character jump (move verticaly up)
			{
				isJumping = true;
				velocityY = jumpSpeed;
			}
			else if(event.getCode() == KeyCode.A) // movement to right
			{
				main.setX(x -= moveSpeed);
			}
			else if(event.getCode() == KeyCode.D) // movement to left
			{
				main.setX(x += moveSpeed);
			}
			
		});
		AnimationTimer timer = new AnimationTimer()
		{
			
			@Override
			public void handle(long now) {
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
				double heroHeight = objects.get(0).getHeight();
				double groundTop = canvas.getHeight();
				
				// drawing the ground for the level
				for(GameObject gr:ground)
				{
					if (gr.getY() < groundTop) // finding out the ground level
					{
				        groundTop = gr.getY();
				    }
					
					gr.update();
					
				}
				double heroY = groundTop - heroHeight + 20; // placing the character on the ground level 
				/**
				 * isJumping functionality
				 * essentially whenever the w is pressed isJumping is changed to true.
				 * This using velocity and gravity changes the y position of the character
				 * When velocity reaches 0 jump is completed so isJumping is set to false again
				 */
				if(isJumping == false)
				{
					y = heroY;
				}
				else
				{
					velocityY -= gravity;
					y -= velocityY;
					if(y >= heroY)
					{
						y = heroY;
						velocityY = 0.0;
						isJumping = false;
					}
				}
				objects.get(0).setY(y);
				objects.get(0).setX(x);
				
				for(GameObject obj:objects)
				{
					obj.update();	
				}
				
				
			}
			
		};
		
		timer.start();
		
		
		
		
	}

}
