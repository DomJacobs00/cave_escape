package age491.cave_escape;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar extends HBox {
	private Rectangle healthBar;
	private double maxWidth;
	private double maxHeight;
	
	public HealthBar(double width, double height, Color color)
	{
		maxWidth = width;
		maxHeight  = height;
		
		healthBar = new Rectangle(maxWidth, maxHeight);
		healthBar.setFill(color);
		
		this.getChildren().add(healthBar);
		
		
	}
	
	public void updateHealth(double healthPercentage)
	{
		double newWidth = maxWidth * healthPercentage;
		healthBar.setWidth(newWidth);
	}
}

