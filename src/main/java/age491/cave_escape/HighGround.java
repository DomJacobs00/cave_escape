package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HighGround extends GameObject {

	public HighGround(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		img = new Image(LowGround.class.getResource("/ground_with_elevation.png").toExternalForm());
	}
	@Override
	public void update()
	{
		if(img!=null)
		{
			gc.drawImage(img,  x, 400,  100,200);
		}
		setHeight(200);
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

}
