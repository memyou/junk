package junk.event;

import junk.life.Human;

//対人イベントの有無
public class EnemyEvent implements Event{
	private Human enemy;
	
	public void setEnemy(Human enemy) { this.enemy = enemy; }
	public Human getEnemy() { return this.enemy; }

	@Override
	public void eventInfo() {
		if(this.enemy == null) {
			//盗賊がいない時
		}else {
			//盗賊がいる時
		}
	}

}
