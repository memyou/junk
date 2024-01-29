package junk.life;

public abstract class Human{
	private String name;
	private int strength;
	
	public Human(String name) {
		this.name = name;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public String getName() { return this.name; }
	public int getStrength() { return this.strength; }
	
	public abstract void status();
}
