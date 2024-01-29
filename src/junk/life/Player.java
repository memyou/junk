package junk.life;

public class Player extends Human{
	
	
	public Player(String name,int strength) {
		super(name);
		super.setStrength(strength);
	}
	
	@Override
	public void status() {
		System.out.printf("名前；%S\n",super.getName());
		//来歴、短いフレーバーテキスト
		System.out.println("");
	}
	
}
