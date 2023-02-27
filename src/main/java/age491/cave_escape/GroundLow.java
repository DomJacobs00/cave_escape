package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GroundLow extends GameObject {

	public GroundLow(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(GroundLow.class.getResource("ground_no_elevation.png").toExternalForm());
	}

}
