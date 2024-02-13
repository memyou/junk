package junk.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import junk.item.Item;
import junk.life.Appraiser;
import junk.life.Begger;
import junk.life.Human;
import junk.life.Player;
import junk.life.Thief;

//対人イベント(戦闘関連)の処理について
public abstract class VsNpcSystem {
	
	//接敵
	public static void encountEnemy(int select,Scanner scNum,Player pl,Score score,Human enemy) {
		//インスタンス調査と登場時セリフ
		if(enemy instanceof Thief) {
			Thief thief = (Thief)enemy;
			thief.encountTxt();
		}
		if(enemy instanceof Begger) {
			Begger begger = (Begger)enemy;
			begger.encountTxt();
		}
		
		
		while(true) {
			do {
				System.out.printf("\n「%sに遭遇してしまった。どう切り抜けよう？」\n",enemy.getName());
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
				persuade(pl,enemy,score);
				return;
			case 4:
				pl.observation(enemy);
				break;
			}
		
		}
	
	}
	
	//戦う
	//戦闘、相手が鑑定士の場合は問答無用で負けかつ全アイテムと所持金の半分を奪われる
	public static void battle(Player pl,Human human,Score score) {
		score.battleEnemy(); //戦闘回数計測
		
		//プレイヤーの戦闘突入セリフ
		pl.battle(human);
		
	if(human instanceof Appraiser) {
		//鑑定士を襲撃した時
		score.battleAp(); //鑑定士襲撃計測
		score.getLoseBattle(); //敗北計測
		//鑑定士の勝利
		pl.loser(human);
	}else {
		GameSystem.elapsed(); //時間経過表現
		
		//鑑定士以外の敵を攻撃した時
		if(pl.getBodyType() > human.getBodyType()) {
			score.winBattle(); //勝利計測
			System.out.printf("%sとの戦闘に勝利しました。\n",human.getName());
			pl.winner(human);
		}else if(pl.getBodyType() < human.getBodyType()) {
			//プレイヤーの負け
			score.loseBattle(); //敗北計測
			System.out.printf("%sとの戦闘に敗北しました。\n",human.getName());
			pl.loser(human);
		}else if(pl.getBodyType() == human.getBodyType()){
			//引き分け
			score.drawBattle(); //引き分け計測
			System.out.printf("%sとの戦闘は引き分けでした。\n",human.getName());
			pl.draw(human);
		}
	}
}

	
	//逃げる
	//敵から逃げる、失敗した時はさらに確率で勝敗決定
	public static void run(Player pl,Human human,Score score) {
		if(human instanceof Begger) {
			//物乞いなら確定で逃走可能
			score.runaway(); //逃走計測
			pl.run(human);
		}else{
			GameSystem.elapsed(); //時間経過表現
			
			if(pl.getBodyType() < human.getBodyType()) {
				//逃げられる
				score.runaway(); //逃走計測
				System.out.printf("%sから逃げることに成功しました。\n",human.getName());
				pl.run(human);
			}else if(pl.getBodyType() > human.getBodyType()) {
				//逃げられない、負け確定
				score.loseBattle(); //敗北計測
				System.out.printf("%sから逃げることに失敗しました。\n",human.getName());
				pl.loser(human);
			}else{
				//確率で逃げられる
				//0なら逃走可能、1ならさらに確率で勝敗決定
				int rand = new Random().nextInt(2);
				int fight = new Random().nextInt(2);
				
				if(rand == 0) {
					//逃走出来たとき
					score.runaway(); //逃走計測
					System.out.printf("%sから逃げることに成功しました。\n",human.getName());
					pl.run(human);
				}else{
					//逃走できなかった時
					System.out.printf("%sから逃げることに失敗しました。戦闘に入ります。\n",human.getName());
					
					GameSystem.elapsed(); //時間経過表現
					
					if(fight  == 0) {
						//勝ち
						score.winBattle(); //勝利計測
						pl.winner(human);
					}else {
						//負け
						score.loseBattle(); //敗北計測
						pl.loser(human);
					}
				}
			}
		}
	}

	
	//敵と対話する、確率で成功、失敗すると確率で勝敗決定
	public static void persuade(Player pl,Human human,Score score) {
		//インスタンス調査}
		Begger begger = null;
		if(human instanceof Begger) {
			begger = (Begger)human;
		}
		//成功の可否を判定
		int rand = new Random().nextInt(2);
		int fight = new Random().nextInt(2);
		
		
		//
		if(begger != null) {
			//物乞いなら確定で成功
			score.canPersuade(); //説得計測
			pl.persuade(begger);
		}else {
			//物乞い以外、確率で成功
			if(rand == 0) {
				//対話成功、損害なし
				score.canPersuade(); //説得計測
				pl.persuade(human);
				System.out.printf("%sの説得に成功しました。損害はありません。\n",human.getName());
			}else {
				//対話失敗、確率で勝敗
				System.out.printf("%sの説得に失敗しました。戦闘に入ります。\n",human.getName());
				
				GameSystem.elapsed(); //時間経過表現
				
				if(fight == 0) {
					//勝ち
					score.winBattle(); //勝利計測
					System.out.println("戦闘に勝利しました。");
					pl.winner(human);
				}else {
					//負け
					score.loseBattle(); //敗北計測
					System.out.println("戦闘に敗北しました。");
					pl.loser(human);
				}	
			}
		}
	}
	
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
	
	//盗賊にアイテムを奪われる処理、インデックス０から最大３個
	public static int rubItem(Player pl) {
		//全所持アイテム数
		int listSize = pl.getAllItemList().size();
		//奪われる個数、ランダムで最大３個
		int rand = new Random().nextInt(Thief.MAX_RUB) + 1;
		//実際に奪われた個数
		int rub = 0;
		List<Item> list = new ArrayList<Item>();
		
		if(listSize == 0) {
			System.out.println("奪われるアイテムはありませんでした。");
			return rub;
		}else if(listSize <= rand) {
			//手持ちが奪われる個数以下なら全部奪われる
			pl.getAllItemList().clear();
			System.out.println("全てのアイテムが奪われてしまいました。");
			return (rub = listSize);
		}else {
			//前から最大３個奪われる
			for(int i = 0;i < rand;i++) {
				//奪われるものを取り出す
				list.add(pl.getAllItemList().get(i));
			}
			//奪われたものを所持リストから削除
			pl.getAllItemList().removeAll(list);
			rub = rand;
			System.out.printf("%d個のアイテムが奪われてしまいました。\n",rub);
			return rub;
		}
	}
	
	//物乞いに施す、～所持金額の半分（のはず）
	public static void give(Player pl) {
		double rand = new Random().nextDouble(5) + 1;
		int haveMoney = pl.getMoney();
		int giveMoney = (int)(haveMoney * (rand / 10));
		pl.setMoney((haveMoney - giveMoney));
		System.out.printf("%sは%dZを施しました。\n",pl.getName(),giveMoney);
	}
	
	//物乞いからアイテムを譲られる処理、ランダムでＮ以上のアイテムを１つ取得
	public static void take(Player pl,Begger begger) {
		pl.getAllItemList().add(new Item(begger));
		System.out.println("物乞いからアイテムを一つ受け取りました。");
	}
	
	
	
}
