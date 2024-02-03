package junk.event;

import junk.life.Human;

//対人イベントの有無
public class EnemyEvent implements Event{
	private Human enemy;
	
	//コンストラクタ
	public EnemyEvent(Human enemy) {
		this.enemy = enemy;
	}
	
	@Override
	public void eventInfo() {
		if(this.enemy == null) {
			//敵がいない時
		}else {
			//敵がいる時
		}
	}
	public void setEnemy(Human enemy) { this.enemy = enemy; }
	public Human getEnemy() { return this.enemy; }
}
