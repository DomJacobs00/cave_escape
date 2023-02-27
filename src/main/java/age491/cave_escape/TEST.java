package age491.cave_escape;

import javafx.animation.AnimationTimer;

public class TEST {
	AnimationTimer timer = new AnimationTimer()
	{
	    @Override
	    public void handle(long now) {
	        
			Object gc;
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

	        double heroHeight = objects.get(0).getHeight();
	        double groundTop = canvas.getHeight();

	        // Get the x-coordinate of the character
	        double heroX = objects.get(0).getX();

	        // Check if the character has reached the specific x-coordinate for ground image change
	        if (heroX >= 400) {
	            // Remove the old ground objects from the ground ArrayList
	            ground.clear();

	            // Add new ground objects with different images
	            for (int t = 0; t < 8; t++) {
	                double gx = t * 100, gy = 500;

	       
	                
	                if (x > 400 && !reachedThreshold)
	                {
	                    ground.add(factory.createProduct("groundHigh", gx, gy));
	                    reachedThreshold = true;
	                }
	                else if (reachedThreshold)
	                {
	                    ground.add(factory.createProduct("groundRandom", gx, gy));
	                }
	                else
	                {
	                    ground.add(factory.createProduct("groundLow", gx, gy));
	                }
	            }
	        }

	        // drawing the ground for the level
	        for (GameObject gr : ground) {
	            if (gr.getY() < groundTop) // finding out the ground level
	            {
	                groundTop = gr.getY();
	            }

	            gr.update();
	        }

	        // Update the character's position
	        if (isJumping)
	        {
	            velocityY += gravity;
	            y += velocityY;

	            if (y + heroHeight > groundTop)
	            {
	                y = groundTop - heroHeight;
	                isJumping = false;
	            }
	        }
	        main.setX(x);
	        main.setY(y);
	        main.update();
	    }
	};

}
