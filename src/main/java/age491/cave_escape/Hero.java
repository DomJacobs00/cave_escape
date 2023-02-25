package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Hero extends GameObject {

	public Hero(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(Hero.class.getResource("test.png").toExternalForm());
	}
	

}
