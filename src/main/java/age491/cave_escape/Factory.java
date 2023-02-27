package age491.cave_escape;

import javafx.scene.canvas.GraphicsContext;

public class Factory implements FactoryIF {
	GraphicsContext gc;
	@Override
	public GameObject createProduct(String discrim, double x, double y) {
		if(discrim.equals("hero"))
		{
			return new Hero(x,y,gc);
		}
		if(discrim.equals("groundLow"))
		{
			return new GroundLow(x, y,gc);
		}
		if(discrim.equals("groundRandom"))
		{
			return new GroundHigh(x,y,gc);
		}
		return null;
	}
	public Factory(GraphicsContext gc) {
		super();
		this.gc = gc;
	}
	
	
}
