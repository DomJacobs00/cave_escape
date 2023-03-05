package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Hero extends GameObject {
	
	
	public Hero(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(Hero.class.getResource("standing.png").toExternalForm());
		setHeight(100);
		setWidth(100);
	}
	@Override
	public void setWidth(int width)
	{
		this.width = width;
	}
	@Override
	public void setHeight(int height)
	{
		this.height = height;
	}
	public void movingLeft()
	{
		img = new Image(Hero.class.getResource("moving_left.png").toExternalForm());
	}
	public void movingRight()
	{
		img = new Image(Hero.class.getResource("moving_right.png").toExternalForm());
	}
	public void jumping()
	{
		img = new Image(Hero.class.getResource("jumping.png").toExternalForm());
	}
}
