package age491.cave_escape;
import java.util.ArrayList;

import age491.factoryapp.GameObject;
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
	Factory factory;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	
	double x = 0.0, y=0.0;
	double moveSpeed = 5.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
	
		root = new Pane();
		scene = new Scene(root,800,600);
		canvas = new Canvas(800,600);
		gc = canvas.getGraphicsContext2D();
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		root.getChildren().add(canvas);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		factory = new Factory(gc);
		objects.add(factory.createProduct("hero", x, y));
		
		GameObject main = objects.get(0);
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.W)
			{
				main.setY(x -= moveSpeed);
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

			@Override
			public void handle(long now) {
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				for(GameObject obj:objects)
				{
					obj.update();
					
					
				}
				main.setX(x);
				main.setY(y);
				
			}
		};
		
		timer.start();
		
		
		
		
	}

}
