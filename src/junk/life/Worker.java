package junk.life;

public abstract class Worker extends Human{

	public Worker(String name,int bodyType) {
		super(name, bodyType);
	}
	
	
	public abstract void winner(Human human);
	public abstract void loser(Human human);
	public abstract void draw(Human human);
	public abstract void run(Human human);
	public abstract void persuade(Human human);
}
