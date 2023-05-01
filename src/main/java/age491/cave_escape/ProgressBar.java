package age491.cave_escape;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class ProgressBar extends HBox {
	
	private Label progressLabel;
	private ImageView bookImage;
	
	public ProgressBar()
	{
		progressLabel = new Label("x 0");
		progressLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");
		Image book = new Image(ProgressBar.class.getResource("book.png").toExternalForm());
		bookImage = new ImageView(book);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);
		getChildren().addAll(progressLabel, bookImage);
	}
	public void updateProgress(int newInventorySize)
	{
		progressLabel.setText("x " + newInventorySize);
	}
}
