package junk.life;

import java.util.List;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;
import junk.system.GameSystem;
import junk.system.Score;

public class Player extends Worker{
	private int whereFieldNum;
	private int money;
	private List<Item> allItemList;
	
	
	//新規作成用コンストラクタ
	public Player(String name,int bodyType,List<Item> allItemList) {
		super(name,bodyType);
		this.money = 0;
		this.allItemList = allItemList;
	}
	//コンテニュー用コンストラクタ
	public Player(String name,int bodyType,int money) {
		super(name,bodyType);
		this.money = money;
	}
	
	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		System.out.printf("現在の所持金：%dZ\n" + this.getMoney());
		//フレーバーテキスト
		System.out.println("");
	}
	
	//現在位置確認
	public void whereNow(Field[][] field,Player pl) {
		System.out.println("\n「現在位置は……」");
		System.out.printf("区画No.\n",this.getWhereFieldNum());
		//地図情報呼び出し
		GameSystem.mapStatus(field,pl);
	}
	
	//アイテム発掘
	public void excavateItem(Field[][] field,Score score) {
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				if(field[i][j].getFieldNum() == this.whereFieldNum) {
					if(field[i][j].getItem() == null) {
						System.out.println("「ここにはもう何もないようだ。」");
					}else {
						score.allDigItem();
						System.out.println("\n採掘を開始します。");
						for(int k = 0;k < 3;k++) {
							GameSystem.elapsed();
						}
						this.allItemList.add(field[i][j].getItem());
						field[i][j].setItem(null);
						score.countTurn();
						System.out.println("アイテムを入手しました。本日の残り発掘回数：" + (Score.MAX_TURN - score.getCoutnTurn()));
						if(score.getCoutnTurn() == Score.MAX_TURN) {
							score.countDay();
							System.out.println("本日の発掘を終了します。残り活動日数：" + (Score.MAX_DAY - score.getCountDay()));
							if(score.getCountDay() == Score.MAX_DAY) {
								System.out.println("活動限界日数を迎えました。");
							}
						}
					}
				}
			}
		}
	}
	
	//先へ進む
	public void moveOn(Scanner scNum,Field[][] field) {
		int select = 0;
		while(true) {
			System.out.println("\n「先へ進もう」");
			do {
				System.out.println("「どっちの方向へ行こうか」");
				System.out.print("1.東 2.西 3.南 4.北 >>");
				select = scNum.nextInt();
				if(0 >= select || select > 5) {
					System.out.println("方角は1～4を選択して下さい。");
				}
			}while(0 >= select || select > 5);
			switch(select) {
			case 1: //東
				this.moveToEast();
				return;
			case 2: //西
				this.moveToWest();
				return;
			case 3: //南
				this.moveToSouth();
				return;
			case 4: //北
				this.moveToNorth();
				return;
			}
		}
	}
	
	//東へ
	public void moveToEast() {
		if((this.getWhereFieldNum() % Field.AREA) == 0) {
			System.out.println("「これ以上東に行くことはできない。」");
			System.out.println("移動することができませんでした。現在位置：" + this.getWhereFieldNum());
		}else {
			System.out.println("東に一つ区画を移動しました。");
			int move = this.getWhereFieldNum();
			this.setWhereFieldNum(move + 1);
			System.out.println("現在位置：" + this.getWhereFieldNum());
		}
	}
	
	//西へ
	public void moveToWest() {
		if((this.getWhereFieldNum() % Field.AREA) == 1) {
			System.out.println("「これ以上西に行くことはできない。」");
			System.out.println("移動することができませんでした。現在位置：" + this.getWhereFieldNum());
		}else {
			System.out.println("西に一つ区画を移動しました。");
			int move = this.getWhereFieldNum();
			this.setWhereFieldNum(move);
			System.out.println("現在位置：" + this.getWhereFieldNum());
		}
	}
	
	//南へ
	public void moveToSouth() {
		int minNum = 1 + Field.AREA * (Field.AREA - 1);
		int maxNum = (int)Math.pow(Field.AREA,2);
		if(minNum <= this.getWhereFieldNum() || this.getWhereFieldNum() >= maxNum) {
			System.out.println("「これ以上南に行くことはできない。」");
			System.out.println("移動することができませんでした。現在位置：" + this.getWhereFieldNum());
		}else {
			System.out.println("南に一つ区画を移動しました。");
			int move = this.getWhereFieldNum();
			this.setWhereFieldNum(move + Field.AREA);
			System.out.println("現在位置：" + this.getWhereFieldNum());
		}
	}
	
	//北へ
	public void moveToNorth() {
		int minNum = 1;
		int maxNum = Field.AREA;
		if(this.getWhereFieldNum() == minNum || this.getWhereFieldNum() <= maxNum) {
			System.out.println("「これ以上北に行くことはできない。」");
			System.out.println("移動することができませんでした。現在位置：" + this.getWhereFieldNum());
		}else {
			System.out.println("北に一つ区画を移動しました。");
			int move = this.getWhereFieldNum();
			this.setWhereFieldNum(move - Field.AREA);
			System.out.println("現在位置：" + this.getWhereFieldNum());
		}	
	}
	
	//所持全アイテム確認
	public void haveItem() {
		if(this.allItemList.size() == 0) {
			System.out.println("「まだ何も発掘していない。」");
		}else {
			System.out.println("\n***所持アイテムリスト***");
			for(int i = 0;i < this.allItemList.size();i++) {
				System.out.print((i + 1) + "：");
				this.allItemList.get(i).displayStatus();
			}
		}
	}
	
	//鑑定士の呼び出し
	public void callAppraiser(Appraiser appraiser,Scanner scNum,int select,Score score) {
		score.countCallAp();
		System.out.println("\n「鑑定士を呼ぼう。」");
		GameSystem.appraisal(this,appraiser,scNum,select,score);
	}
	
	//鑑定士にアイテムを売る
	public void saleItem(Appraiser appraiser,Scanner scNum,Score score) {
		System.out.println("\n「アイテムを売ろう。」");
		
		if(this.getAllItemList().size() == 0) {
			//所持アイテム0なら
			System.out.println("「……と思ったけど何も持っていなかった。」");
			return;
		}else {
			//1以上アイテムを所持していたら
			GameSystem.saleItem(this,appraiser,scNum,score);
		}
	}
	
	//情報を確認する
	public void displayData(Scanner scNum,Field[][] field,Player pl,int select) {
		System.out.println("\n「何の情報を確認しよう？」");
		//情報確認操作呼び出し
		GameSystem.displayData(scNum,field,this,select);
	}
	
	
	//仕事を休む
	public void restWork(Scanner scNum,int select,Score score) {
		System.out.println("「\n発掘作業を一回休もうか？」");
		//休息操作呼び出し
		GameSystem.restWork(scNum,select,score);
	}
	
	//仕事を終える
	public void endWork(Scanner scNum,int select,Score score) {
		
		
		
	}
	
	//相手を観察する
	public void observation(Human human) {
		System.out.println("\n「相手を観察してみよう。」");
		human.showStatus();
	}
	
	//敵を説得する
	public void persuade(Human human) {
		
	}
	
	//プレイヤー勝利
	@Override
	public void winner() {
		
	}
	
	//プレイヤー敗北
	@Override
	public void loser(Human human) {
		//インスタンス調査
		if(human instanceof Appraiser) {
			//鑑定士に敵対した時
			System.out.println("「ラジアーパと敵対するんじゃなかった……。何なんだ、あの強さは。」");
		}if(human instanceof Thief) {
			//盗賊に敵対した時
			if(this.getAllItemList().size() == 0) {
			System.out.println("「せっかく採掘したアイテムが全部奪われてしまった……。」");
			}else {
				System.out.println("「酷い目にあった……でもアイテムを全部奪われなくて良かった。」");
			}
		}
	}
	
	//引き分け
	@Override
	public void draw() {
		System.out.println("「何とかやり過ごすことが出来た。」");
	}
	
	//逃走
	@Override
	public void run() {
		System.out.println("「よし、無事離れることが出来た。」");
	}
	
	//売却して収入を得る
	public void income(int money) { this.money += money; }
	
	//getter,setter
	public int getWhereFieldNum() { return this.whereFieldNum; }
	public void setWhereFieldNum(int whereFieldNum) { this.whereFieldNum = whereFieldNum; }
	
	public int getMoney() { return this.money; }
	public void setMoney(int money) { this.money = money;}
	
	public List<Item> getAllItemList(){ return this.allItemList; }
	public void setAllItemList(List<Item> allItemList) { this.allItemList = allItemList; }
	
	
	
	
}
