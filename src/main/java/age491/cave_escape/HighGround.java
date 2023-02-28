package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HighGround extends GameObject {

	public HighGround(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(LowGround.class.getResource("ground_with_elevation.png").toExternalForm());
	}

}
