package age491.cave_escape;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class HealthBar extends HBox {

	Image heartFull = new Image(HealthBar.class.getResource("/heartFull.png").toExternalForm());
	Image heartEmpty = new Image(HealthBar.class.getResource("/heartEmpty.png").toExternalForm());
	
	
	public HealthBar(int numberOfHearts)
	{
		super(10);//setting spacing to 10px
		setAlignment(Pos.TOP_LEFT); //sets alignment of the box
		//loading the full heart images to display full HP
		initHealth();
		 
	}
	public void initHealth()
	{
		for (int i=0; i < 5; i++)
		{
			ImageView healthImageView = new ImageView(heartFull);
			getChildren().add(healthImageView);
		}
	}
	public void updateHealth(int health)
	{
		// Clear the current health bar
        getChildren().clear();

        // Add the appropriate number of full hearts
        for (int i = 0; i < health; i++) {
            ImageView healthImageView = new ImageView(heartFull);
            getChildren().add(healthImageView);
        }

        // Add empty hearts for the remaining health slots
        int maxHealth = 5; // You can adjust this value based on your requirements
        for (int i = health; i < maxHealth; i++) {
            ImageView healthImageView = new ImageView(heartEmpty);
            getChildren().add(healthImageView);
        }
		
	}

}
