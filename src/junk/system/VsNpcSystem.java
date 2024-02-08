package junk.system;

import java.util.Random;
import java.util.Scanner;

import junk.event.EnemyEvent;
import junk.field.Field;
import junk.item.Item;
import junk.life.Appraiser;
import junk.life.Begger;
import junk.life.Human;
import junk.life.Player;
import junk.life.Thief;

//対人イベント(戦闘関連)の処理について
public abstract class VsNpcSystem {
	//接敵
	static void encountEnemy(int select,Scanner scNum,Player pl,Score score,Field fieldEvent) {
		EnemyEvent ee = null;
		Human enemy = null;
		
		score.encountEnemy();
		
		//EventがEnemyEventかの調査
		if(fieldEvent.getEvent() instanceof EnemyEvent) {
			ee = (EnemyEvent)fieldEvent.getEvent();
			enemy = ee.getEnemy();
		}
		
		//Eventの中身の敵が誰か調査
		if(enemy instanceof Thief) {
			//盗賊登場セリフ
			enemy = (Thief)ee.getEnemy();
			
		}else if(enemy instanceof Begger) {
			//物乞い登場セリフ
			enemy = (Begger)ee.getEnemy();
			
		}
		
		
		while(true) {
			do {
				System.out.printf("\n「%sと遭遇してしまった。どうしよう？」\n",ee.getEnemy().getName());
				System.out.print("1.戦う 2.逃げる 3.対話する 4.観察する >>");
				select = scNum.nextInt();
				if(0 >= select || select > 5) {
					System.out.println("選択肢は1～4を入力してください。");
				}
			}while(0 >= select || select > 5);
			
			switch(select) {
			case 1:
				battle(pl,enemy,score);
				return;
			case 2:
				run(pl,enemy,score);
				return;
			case 3:
				persuade(pl,enemy);
				return;
			case 4:
				pl.observation(enemy);
				break;
			}
		
		}
	
	}
	
	//戦う
	//戦闘、相手が鑑定士の場合は問答無用で負けかつ全アイテムと所持金の半分を奪われる
	static void battle(Player pl,Human human,Score score) {
	
	//敵性存在のインスタンス調査
	Appraiser appraiser = (Appraiser)human.investigation(human);
	Thief thief = (Thief)human.investigation(human);
	Begger begger = (Begger)human.investigation(human);
	
	score.battleEnemy();
	if(appraiser != null) {
		//鑑定士を襲撃した時
		score.battleAp();
		score.getLoseBattle();
		appraiser.winner(pl);
		pl.loser(appraiser);
	}else {
		//鑑定士以外の敵を攻撃した時
		if(pl.getBodyType() > human.getBodyType()) {
			//プレイヤーの勝ち
			score.winBattle();
			if(thief != null) {
				//対盗賊
				thief.loser(pl);
				
				//
			}
			if(begger != null) {
				//対物乞い
				begger.loser();
				
				//
			}
		}else if(pl.getBodyType() < human.getBodyType()) {
			//プレイヤーの負け
			score.loseBattle();
			if(thief != null) {
				//対盗賊
				rubItem(pl);
				thief.winner();
				
				pl.loser(thief);
				//
			}
		}else if(pl.getBodyType() == human.getBodyType()){
			//引き分け
			score.drawBattle();
			if(thief != null) {
				//対盗賊
				thief.draw();
				
				pl.draw();
			}
		}
	}
}

	
	//逃げる
	//敵から逃げる、失敗した時はさらに確率で勝敗決定
	public static void run(Player pl,Human human,Score score) {
		//インスタンスの調査
		Thief thief = (Thief)human.investigation(human);
		Begger begger = (Begger)human.investigation(human);
		
		if(begger != null) {
			//物乞いなら確定で逃走可能
			score.runaway();
			begger.run();
			pl.run();
		}else{
			if(pl.getBodyType() < human.getBodyType()) {
				//逃げられる
				score.runaway();
				
				if(thief != null) {
					//対盗賊
					thief.run(); //※逃げられた時の反応
					pl.run();
				}
				
			}else if(pl.getBodyType() > human.getBodyType()) {
				//逃げられない
				score.loseBattle();
				
				if(thief != null) {
					//対盗賊
					thief.winner();
					pl.loser(thief);
				}
			}else {
				//確率で逃げられる
				//0なら逃走可能、1ならさらに確率で勝敗決定
				int rand = new Random().nextInt(2);
				
				if(rand == 0) {
					//逃走出来たとき
					score.runaway();
				
					if(thief != null) {
						//盗賊の場合
						thief.run();
						
					}
					pl.run();
				}else{
					//逃走できなかった時
					score.loseBattle();
					
					if(rand  == 0) {
						//勝ち
						score.winBattle();
						
						if(thief != null) {
							//対盗賊
							thief.loser(pl);
						}
						pl.run();
					}else {
						//負け
						score.loseBattle();
						
						if(thief != null) {
							//対盗賊
							thief.winner();
						}
						pl.loser(thief);
					}
				}
			}
		}
	}

	
	//敵と対話する、確率で成功、失敗すると確率で勝敗決定
	public static void persuade(Player pl,Human human) {
		//インスタンス調査
		Thief thief = (Thief)human.investigation(human);
		Begger begger = (Begger)human.investigation(human);
		
		//
		if(begger != null) {
			//物乞いなら確定で成功
			pl.persuade(begger);
			
		}else {
			
			
			
		}
		
		
		
	}
	
	
	
	//観察する
	
	
	
	//鑑定士がプレイヤーから接収する処理
	public static void confiscation(Player pl) {
		//プレイヤーの全アイテムと所持金の半分を没収
		pl.getAllItemList().clear();
		int confiscation = pl.getMoney() / 2;
		pl.setMoney(confiscation);
	}
	
	//盗賊からアイテムを取得する処理
	public static void obtainItem(Player pl) {
		//入手個数
		int rand = new Random().nextInt(3);
		
		for(int i = 0;i < rand;i++) {
			pl.getAllItemList().add(new Item());
		}
	}
	
	//盗賊にアイテムを奪われる処理、最大３個
	public static void rubItem(Player pl) {
		//全所持アイテム数
		int listSize = pl.getAllItemList().size();
		//奪われるアイテム
		int robItem = new Random().nextInt(listSize);
		//奪われる個数、最大３個
		int rand = new Random().nextInt(3);
		
		if(listSize < rand) {
			//手持ちが奪われる個数より低ければ全部奪われる
			pl.getAllItemList().clear();
		}else {
			//ランダムに最大３個奪われる
			for(int i = 0;i < rand;i++) {
				pl.getAllItemList().remove(robItem);	
			}
		}
	}
	
	//物乞いからアイテムを譲られる処理
	
	
	
	
}
