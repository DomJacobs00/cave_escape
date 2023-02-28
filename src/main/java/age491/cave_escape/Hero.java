package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Hero extends GameObject {
	Image no_movement;
	Image left_movement;
	Image right_movement;
	Image img;
	
	public Hero(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(Hero.class.getResource("test.png").toExternalForm());
	}
	

}
