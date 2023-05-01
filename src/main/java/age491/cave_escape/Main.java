package age491.cave_escape;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Pane root;
    private Scene gameScene;
    private Scene startMenuScene; // Make startMenuScene an instance variable
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        // Start menu
        createStartMenu(primaryStage);

        // Configure primaryStage
        primaryStage.setTitle("Your Game");
        primaryStage.setScene(startMenuScene);
        primaryStage.show();
    }

    private void createStartMenu(Stage primaryStage) {
        Button startButton = new Button("Start");
        startButton.setMinSize(150, 60);
        startButton.setStyle("-fx-background-color: #FF5733; -fx-font-size: 20;");

        Image image = new Image(getClass().getResource("/your-image-file.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        VBox startMenuPane = new VBox(20, imageView, startButton);
        startMenuPane.setAlignment(Pos.CENTER);
        startMenuPane.setStyle("-fx-background-color: black;");
        startMenuScene = new Scene(startMenuPane, 800, 600);

        startButton.setOnAction(e -> {
            initGame();
            primaryStage.setScene(gameScene);
        });
    }

    private void initGame() {
        root = new Pane();
        gameScene = new Scene(root, 800, 600);
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int variable = 10; // Your variable

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Add your game logic here
                variable--;

                if (variable == 0) {
                    // Go back to the main menu
                    Stage primaryStage = (Stage) gameScene.getWindow();
                    primaryStage.setScene(startMenuScene);
                    this.stop(); // Stop the current AnimationTimer
                }
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

