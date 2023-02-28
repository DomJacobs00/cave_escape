package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Skeleton extends GameObject {

	public Skeleton(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(Skeleton.class.getResource("skeleton_still.png").toExternalForm());
	}
	public void movingLeft()
	{
		img = new Image(Hero.class.getResource("skeleton_moving_left.png").toExternalForm());
	}
	public void movingRight()
	{
		img = new Image(Hero.class.getResource("skeleton_moving_right.png").toExternalForm());
	}
}
