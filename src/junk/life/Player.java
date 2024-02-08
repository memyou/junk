package junk.life;

import java.util.List;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;
import junk.system.GameSystem;
import junk.system.Score;

public class Player extends Human{
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
							System.out.print(":");
							GameSystem.pushEnterKey();
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
	
	//アイテム確認
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
		appraiser.salesTalk(this,scNum,select,score);
	}
	
	//鑑定士にアイテムを売る
	public void saleItem(Appraiser appraiser,Scanner scNum,Score score) {
		int select = 0;
		Item item = null;
		Item saleItem = null;
		int allItem = this.getAllItemList().size();
		int salePrice = 0;
		appraiser.saleItem();
		
		select = scNum.nextInt();
		do {
			System.out.printf("0.全ての鑑定済みアイテムを売却する 1～%d.鑑定済みアイテムを個別に売却する",allItem);
			select = scNum.nextInt();
			if(0 > select || select > (allItem + 1)) {
				System.out.printf("選択肢は0～%dを入力してください。");
			}
			if(select > 1) {
				item = this.getAllItemList().get((select - 1));
				if(item.getIdentified() == false) {
					System.out.println("これは未鑑定のアイテムです。売却には鑑定済みのアイテムを選択してください。");
				}
			}
			
		}while(0 > select || select > (allItem + 1));
		
		if(select == 0) {
			System.out.println("\n「全部売ろう。」");
			for(int i = 0;i < allItem;i++) {
				item = this.getAllItemList().get(i);
				if(item.getIdentified() == true) {
					score.saleItem();
					saleItem = this.getAllItemList().remove(i);
					salePrice = saleItem.salePrice();
					this.income(salePrice);
				}
			}
			System.out.println("全ての鑑定済みアイテムを売却しました。");
			this.haveItem();
		}else {
			System.out.println("「一つずつ売ろう。」");
			score.saleItem();
			saleItem = this.getAllItemList().remove((select - 1));
			salePrice = saleItem.salePrice();
			this.income(salePrice);
		}
	}
	
	//仕事を休む
	public void restWork(Scanner scNum,int select,Score score) {
		System.out.println("「\n発掘作業を一回休もうか？」");
		do {
			System.out.print("休みを取ると発掘回数を１消費します。休みますか？ 1.はい 2.いいえ >>");
			select = scNum.nextInt();
			if(0 >= select || select > 3) {
				System.out.println("選択肢は1または2です。");
			}
		}while(0 >= select || select > 3);
		switch(select) {
		case 1:
			score.countTurn();
			System.out.println("\n発掘作業をしませんでした。１日の残り採掘回数：" + (Score.MAX_TURN - score.getCoutnTurn()));
			if(score.getCountDay() == Score.MAX_DAY && score.getCoutnTurn() == Score.MAX_TURN) {
				System.out.println("「今回の仕事はここまでだ。換金して帰ろう。」");
				
			}
			break;
		case 2:
			System.out.println("「やっぱり作業をしよう。」");
		}
	}
	
	//仕事を終える
	public void endWork(Scanner scNum,int select,Score score) {
		
		
		
	}
	
	//相手を観察する
	public void observation(Human human) {
		System.out.println("\n「相手を観察してみよう。」");
		human.showStatus();
	}
	
	//戦闘、相手が鑑定士の場合は問答無用で負けかつ全アイテムと所持金の半分を奪われる
	public void battle(Human human,Score score) {
		if(super.getBodyType() > human.getBodyType()) {
			//プレイヤーの勝ち
			
		}else if(super.getBodyType() < human.getBodyType()) {
			//プレイヤーの負け
			
		}else {
			//引き分け
			
		}
	}
	
	//敵から逃げる
	public void run(Human human,Score score) {
		if(super.getBodyType() < human.getBodyType()) {
			//逃げられる
			
		}else if(super.getBodyType() > human.getBodyType()) {
			//逃げられない
			
		}else {
			//確率で逃げられる
			
		}
	}
	
	//敵を説得する
	public void persuade(Human human) {
		
		
		
		
		
		
		
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
