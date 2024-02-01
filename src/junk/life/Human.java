package junk.life;

public abstract class Human{
	private String name;
	private int strength;
	
	
	public Human(String name,int strength) {
		this.name = name;
		this.strength = strength;
	}
	
	//ステータス表示
	public abstract void status();
	
	//getter,setter
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public int getStrength() { return this.strength; }
	public void setStrength(int strength) { this.strength = strength; }
		
}
