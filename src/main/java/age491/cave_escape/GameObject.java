package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
	protected Image img;
	protected double x,y;
	protected GraphicsContext gc;
	double height;
	
	
	public GameObject(double x, double y, GraphicsContext gc)
	{
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		double height = 100;
		
		
	}
	public void update(double hy)
	{
		if(img!=null)
		{
			gc.drawImage(img,  x, y,  100,hy);
		}
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	 double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setHeight(double height)
	{
		this.height = height;
	}
	public double getHeight()
	{
		return height;
	}
	
}
