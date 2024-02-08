package junk.life;

public abstract class Human{
	private String name;
	private int bodyType;
	static final String[] BODYTYPE = {"小柄","平均的","大柄"};
	
	public Human(String name,int bodyType) {
		this.name = name;
		this.bodyType = bodyType;
	}
	
	//ステータス表示
	public abstract void showStatus();
	
	@Override
	public String toString() {
		return "名前:" + this.name + "(特徴:" + Human.BODYTYPE[this.bodyType - 1]+ ")";
	}
	
	public abstract Human investigation(Human human);

	//getter,setter
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public int getBodyType() { return this.bodyType; }
	public void setBodyType(int bodyType) { this.bodyType = bodyType; }
		
}
