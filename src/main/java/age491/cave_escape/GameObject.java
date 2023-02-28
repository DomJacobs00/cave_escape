package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
	protected Image img;
	protected double x,y;
	protected GraphicsContext gc;
	protected int height;
	protected int health;
	
	public GameObject(double x, double y, GraphicsContext gc)
	{
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		height = 100;
		health = 100;
		
		
	}
	public void update()
	{
		if(img!=null)
		{
			gc.drawImage(img,  x, y,  100,100);
		}
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getHeight()
	{
		return height;
	}
	public void movingLeft()
	{
		System.out.println("left");
	}
	public void movingRight()
	{
		System.out.println("right");
	}
	
	public void jumping()
	{
		
	}
	/**
	 * 
	 * addition of health bar or something similar to the game to monitor main characters health
	 */
	public void setHealth(int number)
	{
		health = number;
	}
	public int getHealth()
	{
		return health;
		
	}
}
