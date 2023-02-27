package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GroundHigh extends GameObject {

	public GroundHigh(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(GroundHigh.class.getResource("ground_with_elevation.png").toExternalForm());
		setHeight(200);
	}
	public void update()
	{
		
	}

}
