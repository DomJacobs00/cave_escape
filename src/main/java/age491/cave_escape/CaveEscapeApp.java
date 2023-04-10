package age491.cave_escape;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
public class CaveEscapeApp extends Application {
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	ArrayList<GameObject> objects = new ArrayList<GameObject>(); // stores all the characters ( main character and enemies)
	ArrayList<GameObject> ground = new ArrayList<GameObject>(); // stores all objects that create the ground
	ArrayList<GameObject> groundHistory = new ArrayList<GameObject>(); // Keeps track of all created ground objects that can be accessed any time
	Factory factory;
	Movement movement;
	Random groundGenerator ;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	
	double x = -20.0, y=430.0;
	double moveSpeed = 10.0;
	double jumpSpeed = 13.0;
	double gravity = 0.7;
	boolean isJumping = false, isJumpingLeft = false, isJumpingRight = false, isFalling = false;
	boolean isWPressed = false, isAPressed = false, isDPressed = false;
	double velocityY = 2.0;
	int heroLaps = 0;
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
		movement = new Movement();
		
		/**
		 * generating the ground for the level
		 * the ground consists of GroundGenerator objects that are used as 100x100 tiles to be placed on the ground level.
		 * Later the tiles will be randomly generated to make the terrain of the level more different
		 */
		groundGenerator = new Random();
		
		// Creating the initial starting level
		for(int t=0; t<8; t++)
		{
			double gx = t*100, gy = 500;
			ground.add(factory.createProduct("groundLow", gx, gy));
		}
		
		
		
		// Addition of a controllable character hero
		objects.add(factory.createProduct("hero", x, y));
		// Addition of an enemy for testing purposes
		//objects.add(factory.createProduct("skeleton", 300, y));
		
		GameObject main = objects.get(0); // Accessing the character form the list of objects
		
		// movement for the character ( will be moved to separate class maybe?)
		
		scene.setOnKeyPressed(event -> {    
			
			if (event.getCode() == KeyCode.W && !isJumping) // w is binded to make the character jump (move verticaly up)
			{
				isJumping = true;
				isWPressed =true;
				
				y = movement.jump(y);
				velocityY = jumpSpeed;
				
			}
			else if(event.getCode() == KeyCode.A) // movement to left
			{
				isAPressed= true;
				main.setX(x -= moveSpeed);
				// change image to moving left
				main.movingLeft();
				
			}
			else if(event.getCode() == KeyCode.D) // movement to right
			{
				isDPressed = true;
				main.setX(x += moveSpeed);
				main.movingRight();
				
				
			}
			if(isWPressed && isAPressed)
			{
				isJumpingLeft = true;
				
				
			}
			if(isWPressed && isDPressed)
			{
				isJumpingRight = true;
				
			}	
		});
		scene.setOnKeyReleased(event ->{
			if (event.getCode() == KeyCode.W)
			{
				isWPressed = false;
			}
			if (event.getCode() == KeyCode.A)
			{
				isAPressed = false;
			}
			if (event.getCode() == KeyCode.D)
			{
				isDPressed = false;
			}
		
		});
		AnimationTimer timer = new AnimationTimer()
		{
			
			@Override
			
			public void handle(long now) {
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				double heroHeight = objects.get(0).getHeight();
				double groundTop = canvas.getHeight();
				double heroX = objects.get(0).getX();
				/**
				 * Handling of the level design
				 * Whenever the playable character reaches one of the corners of the level the ground arrayList is updated accordingly:
				 * 1. if the character moves to the right corner, the current level is stored in the groundHistory arrayList for later access if needed and a new random level is created, replacing the old one
				 * 2. if the character moves to the left corner, the current level is changed with one from groundHistory arrayList that is used with the heroLaps variable to determine the appropriate level
				 */
				if(heroX > 730)
				{
					x = -20;
					heroLaps++;
					for(int i=0; i< 8; i++)
					{
						groundHistory.add(ground.get(i));
					}
					ground.clear();
					/**
					 * This checks if the ground level is already generated for that location 
					 * if it is then the ground is updated from the groundHistory
					 * else it is generated and added to the groundHistory
					 */
					int check = heroLaps * 8;
					if (check <= groundHistory.size())
					{
						generateRandomLevel();
					}
					else
					{
						// handling the accessing of older levels
					}
					
				}
				else if(heroX < -20)
				{
					x = 730;
					heroLaps--;
					// move the tiles back to the main arrayList 
				}
				
				
				// drawing the ground for the level
				for(GameObject gr:ground)
				{
					
					
					if (gr.getY() < groundTop && !(gr instanceof HighGround) ) // finding out the ground level
					{
				        groundTop = gr.getY(); // need to change this
				    }
					else if(gr instanceof HighGround && (gr.getX() - 50) <= heroX && heroX <= (gr.getX() + 50) )
					{
				        groundTop = gr.getY() -50; // need to change this
				    }
					// Checks what type of ground it is and updates the dimensions accordingly
					if(gr instanceof HighGround)
					{
						HighGround highGround = (HighGround) gr;
						highGround.update();
					}
					
					else
					{
						gr.update();
					}
					
					
					
				}
				/**
				 *  from x = -50 to x = 750 (800)
				 *  Essentially the whole level is from -50 to 750
				 *  the level consists of 8 tiles
				 *  obtaining all the coordinates for movable character (left right top bottom)
				 *  obtaining all the coordinates for current tile (left right top bottom)
				 *  
				 */
					// character right side
					double cRight = objects.get(0).getX()+50;
					// character left side
					double cLeft = objects.get(0).getX()-50;
					// character bottom
					double cBottom = objects.get(0).getY()-50;
					// character top
					double cTop = objects.get(0).getY()+50;
					double tileCorner = 50;
					int curTile = 0;
					// changes the position of the arraylist for ground
					while(objects.get(0).getX() > tileCorner )
					{
						tileCorner += 100;
						curTile ++;
					}
					// tile left side
					double gRight = ground.get(curTile).getX() + 50;
					// tile right side
					double gLeft = ground.get(curTile).getX() - 50;
					// tile top
					double gTop = ground.get(curTile).getY() - (ground.get(curTile).getHeight()/2);
					// tile bottom
					double gBottom = ground.get(curTile).getY() + (ground.get(curTile).getHeight()/2);
					
					//calculating the distances between the character and ground objects
					double distanceRight = gLeft - cRight;
					double distanceLeft = cLeft - gRight;
					double distanceTop = cBottom - gTop;
					double distanceBottom = gBottom - cTop;
							
					// Collision handling
					if(distanceRight < 0 && distanceLeft < 0 && gTop < cBottom)
					{
						double moveX = Math.min(Math.abs(distanceRight), Math.abs(distanceLeft));
						if(distanceRight < distanceLeft)
						{
							System.out.println("Collision right");
							x = x + moveX;
						}
						else
						{
							System.out.println("Collision left");
							x = x - moveX;
							
						}
						
					}
					double dFG = gTop - cBottom;
					if(dFG> 129 && !isJumping && !isJumpingRight && !isJumpingLeft)
					{
						isFalling = true;
						
					}
					
					
					System.out.println("Character bottom: " +  cBottom + "Ground Top: " + gTop + "Tile: "+ curTile);
					//	System.out.println("Tile number 7: Width: "+ ground.get(7).getWidth() +" Height: "+ ground.get(7).getWidth());
				
				double heroY = groundTop - heroHeight + 20; // placing the character on the ground level (needs to be changed)
				
				/**
				 * isJumping functionality
				 * essentially whenever the w is pressed isJumping is changed to true.
				 * This using velocity and gravity changes the y position of the character
				 * When velocity reaches 0 jump is completed so isJumping is set to false again.
				 * Additionally, isJumpingLeft and isJumping right is added to combine movement
				 * and jumping
				 */
				if(isJumping)
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
				if(isJumpingLeft)
				{
					velocityY -= gravity;
					y -= velocityY;
					main.setX(x -= moveSpeed);
					if(y >= heroY)
					{
						y = heroY;
						velocityY = 0.0;
						isJumpingLeft = false;
					}
				}
				if(isJumpingRight)
				{
					velocityY -= gravity;
					y -= velocityY;
					main.setX(x += moveSpeed);
					if(y >= heroY)
					{
						y = heroY;
						velocityY = 0.0;
						isJumpingRight = false;
					}
				}
				if(isFalling)
				{
					if(dFG>80)
					{
						y += 10;
					}
					else
					{
						isFalling = false;
					}
				}
				objects.get(0).setY(y);
				
				objects.get(0).setX(x);
				
				
				for(GameObject obj:objects)
				{
					obj.update();	
				}
				
				
				/**
				 * To have a educational part of the game more interesting the addition of math related questions can be added.
				 * Using a premade collection of math equations and fileChooser it can be accomplished.		
				 */
							
				//FileChooser fileChooser = new FileChooser();
				//fileChooser.setTitle("Select Questions File");
				//fileChooser.getExtensionFilters().addAll(
				//    new ExtensionFilter("CSV Files", "*.csv"),
				//    new ExtensionFilter("All Files", "*.*")
				//);

				//File selectedFile = fileChooser.showOpenDialog(stage);
				//if (selectedFile != null) {
				    // Read the selected file and load the questions
			//	}

						
						
					
					
					
					
				
				
			}
			
		};
		
		timer.start();
		
		
		
		
	}
	public void generateRandomLevel()
	{
		ground.add(factory.createProduct("groundLow", 0, 500));
		for(int generator = 1; generator < 7; generator++)
		{
			int rand_int = groundGenerator.nextInt(2);
			double gx = generator*100;
			if(rand_int == 0)
			{
				ground.add(factory.createProduct("groundLow", gx, 500));
			}
			if(rand_int == 1)
			{
				ground.add(factory.createProduct("highGround", gx, 450));
			}
		}
		ground.add(factory.createProduct("groundLow", 700, 500));
		
	}

}
