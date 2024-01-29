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
	
	public void battle(Human human) {
		if(super.getStrength() > human.getStrength()) {
			//プレイヤーの勝ち
			
		}else if(super.getStrength() < human.getStrength()) {
			//プレイヤーの負け
			
		}else {
			//引き分け
			
		}
	}
	
}
