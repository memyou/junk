package junk.life;

import junk.field.FieldInfo;

public class Player extends Human{
	private int whereFieldNum;
	
	public Player(String name,int strength) {
		super(name);
		super.setStrength(strength);
	}
	
	public void setWhereFieldNum(FieldInfo field) { this.whereFieldNum = field.getFieldNum(); }
	
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
