package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LowGround extends GameObject {
	
	/**
	 * The ground Generator class is esssentially an object that creates randomly generated objects that form the ground.
	 * Initially the game will have a ground level for a certain amount of distance and 
	 * further the player goes the terrain will change.
	 * The list of the surfaces will have several different images that will be essentially the 
	 * indicator of a ground level for the player model
	 */
	public LowGround(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(LowGround.class.getResource("ground_no_elevation.png").toExternalForm());
	}

}
