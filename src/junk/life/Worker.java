package junk.life;

public abstract class Worker extends Human{

	public Worker(String name,int bodyType) {
		super(name, bodyType);
	}
	
	
	public abstract void winner();
	public abstract void loser(Human human);
	public abstract void draw();
	public abstract void run();
	public abstract void persuade(Human human);
}
