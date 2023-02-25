package age491.cave_escape;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CaveEscapeApp extends Application {
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public static void main(String[] args)
	{
		launch(args);
	}
	
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
	}

}
