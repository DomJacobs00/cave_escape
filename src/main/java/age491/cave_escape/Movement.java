package age491.cave_escape;

public class Movement 
{
	double x=0, y=0;
	double moveSpeed = 10.0;
	double jumpSpeed = 13.0;
	double gravity = 0.7;
	double velocityY = 2.0;
	boolean isAPressed = false;
	boolean isDPressed = false;
	public Movement() {
		;
	}
	public double moveLeft(double inx)
	{
		x = inx - moveSpeed;
		return x;
	}
	public double moveRight(double inx)
	{
		x = inx + moveSpeed;
		return x;
	}
	public double jump(double iny)
	{
		velocityY = jumpSpeed;
		velocityY -= gravity;
		y -= velocityY;
		if(y >= iny)
		{
			y = iny;
			velocityY = 0.0;
			
		}
		return iny;
	}
	
	
	/**
	 * 
	 
	scene.setOnKeyPressed(event -> {
	    if (event.getCode() == KeyCode.A) {
	        isAPressed = true;
	    }
	    if (event.getCode() == KeyCode.D) {
	        isDPressed = true;
	    }
	    
	    if (isAPressed && isDPressed) {
	        System.out.println("A and D are pressed together.");
	    }
	});

	scene.setOnKeyReleased(event -> {
	    if (event.getCode() == KeyCode.A) {
	        isAPressed = false;
	    }
	    if (event.getCode() == KeyCode.D) {
	        isDPressed = false;
	    }
	});
	In this example, two boolean variables isAPressed and isDPressed are used to keep track of whether the "A" and "D" keys are currently pressed down. When a key is pressed, its corresponding boolean variable is set to true. When a key is released, its corresponding boolean variable is set to false.

	The if (isAPressed && isDPressed) condition checks if both keys are currently pressed down, and if so, it prints a message to the console.
	 */




	
}
