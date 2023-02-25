package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;

public class Hero extends GameObject {

	public Hero(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(Hero.class.getResource("../").toExternalForm());
	}

}
