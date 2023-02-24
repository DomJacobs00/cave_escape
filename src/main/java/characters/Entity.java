package characters;

import java.util.ArrayList;

public class Entity {
	String name;
	double health;
	double x;
	double y;
	String appearance;
	ArrayList<Items> inventory;
	ArrayList<Abilities> abilities;
	
	public Entity(String name, double health, double x, double y, String appearance)
	{
		this.name = name;
		this.health = health;
		this.x = x;
		this.y = y;
		this.appearance = appearance;
		ArrayList<Items> inventory = new ArrayList<Items>();
		ArrayList<Abilities> abilities = new ArrayList<Abilities>();
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private double getHealth() {
		return health;
	}

	private void setHealth(double health) {
		this.health = health;
	}

	private double getX() {
		return x;
	}

	private void setX(double x) {
		this.x = x;
	}

	private double getY() {
		return y;
	}

	private void setY(double y) {
		this.y = y;
	}

	private String getAppearance() {
		return appearance;
	}

	private void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	
	

}
