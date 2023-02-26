package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
	protected Image img;
	protected double x,y;
	protected GraphicsContext gc;
	int height;
	
	public GameObject(double x, double y, GraphicsContext gc)
	{
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		height = 100;
		
		
	}
	public void update()
	{
		if(img!=null)
		{
			gc.drawImage(img,  x, y,  100,100);
		}
	}
	
	private double getX() {
		return x;
	}
	 void setX(double x) {
		this.x = x;
	}
	 double getY() {
		return y;
	}
	void setY(double y) {
		this.y = y;
	}
	int getHeight()
	{
		return height;
	}
}
