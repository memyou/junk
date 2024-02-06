package junk.life;

import java.util.List;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;

public class Player extends Human{
	private int turnCount;
	public static final int TURNCOUNT = 3;
	private int turn;
	public static final int TURN = 5;

	private int whereFieldNum;
	private int money;
	private List<Item> allItemList;
	private int encount;
	
	
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
		System.out.printf("名前；%S\n",super.getName());
		System.out.println("現在の所持金：" + this.getMoney());
		//フレーバーテキスト
		System.out.println("");
	}
	
	//アイテム発掘
	public void excavateItem(Field[][] field) {
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				if(field[i][j].getFieldNum() == this.whereFieldNum) {
					if(field[i][j].getItem() == null) {
						System.out.println("「ここにはもう何もないようだ。」");
					}else {
						System.out.println("\n採掘を開始します。");
						for(int k = 0;k < 3;k++) {
							System.out.print(":");
							new Scanner(System.in).nextLine();
						}
						this.allItemList.add(field[i][j].getItem());
						field[i][j].setItem(null);
						this.turnCount++;
						System.out.println("アイテムを入手しました。本日の残り発掘回数：" + (Player.TURNCOUNT - this.getTurnCount()));
						if(this.getTurnCount() == Player.TURNCOUNT) {
							this.turn++;
							System.out.println("本日の発掘を終了します。残り活動日数：" + (Player.TURN - this.getTurn()));
							if(this.getTurn() == Player.TURN) {
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
				System.out.print((i + 1) + "；");
				this.allItemList.get(i).displayStatus();
			}
		}
	}
	
	//鑑定士の呼び出し
	public void callAppraiser(Appraiser appraiser,Scanner scNum,int select) {
		System.out.println("『\n鑑定士を呼ぼう。』");
		appraiser.appraisalItem(this,scNum,select);
	}
	//鑑定士にアイテムを売る
	public void saleItem(Appraiser appraiser,Scanner scNum) {
		int select = 0;
		Item item = null;
		Item saleItem = null;
		int allItem = this.getAllItemList().size();
		int salePrice = 0;
		appraiser.saleItem(this);
		
		select = scNum.nextInt();
		do {
			System.out.printf("0.全ての鑑定済みアイテムを売却する 1～%d.鑑定済みアイテムを個別に売却する",allItem);
			select = scNum.nextInt();
			if(0 > select || select > (allItem + 1)) {
				System.out.printf("選択肢は0～%dを入力してください。");
			}
			item = this.getAllItemList().get(select);
			if(item.getIdentified() == false) {
				System.out.println("これは未鑑定のアイテムです。売却には鑑定済みのアイテムを選択してください。");
			}
		}while(0 > select || select > (allItem + 1));
		
		if(select == 0) {
			System.out.println("「\n全部売ろう。」");
			for(int i = 0;i < allItem;i++) {
				item = this.getAllItemList().get(i);
				if(item.getIdentified() == true) {
					saleItem = this.getAllItemList().remove(i);
					salePrice = saleItem.salePrice();
					this.income(salePrice);
				}
			}
			System.out.println("全ての鑑定済みアイテムを売却しました。");
			this.haveItem();
		}else {
			System.out.println("「一つずつ売ろう。」");
			saleItem = this.getAllItemList().remove(select);
			salePrice = saleItem.salePrice();
			this.income(salePrice);
		}
	}
	
	//仕事をやめる
	public void endWork(Scanner scNum) {
		int select = 0;
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
			this.turn++;
			System.out.println("\n発掘作業をしませんでした。１日の残り採掘回数：" + (Player.TURNCOUNT - this.getTurnCount()));
			if(this.getTurn() == Player.TURN && this.getTurnCount() == Player.TURNCOUNT) {
				System.out.println("「今回の仕事はここまでだ。換金して帰ろう。」");
				
			}
			break;
		case 2:
			System.out.println("「やっぱり作業をしよう。」");
		}
	}
	
	//相手を観察する
	public void observation(Human human) {
		System.out.println("『\n相手を観察してみよう』");
		human.showStatus();
	}
	

	public void battle(Human human) {
		if(super.getBodyType() > human.getBodyType()) {
			//プレイヤーの勝ち
			
		}else if(super.getBodyType() < human.getBodyType()) {
			//プレイヤーの負け
			
		}else {
			//引き分け
			
		}
	}
	
	public void run(Human human) {
		if(super.getBodyType() < human.getBodyType()) {
			//逃げられる
			
		}else if(super.getBodyType() > human.getBodyType()) {
			//逃げられない
			
		}else {
			//確率で逃げられる
			
		}
	}
	
	public void income(int money) { this.money += money; }
	
	//getter,setter
	public int getTurnCount() { return this.turnCount;}
	public int getTurn() { return this.turn; }
	
	public int getWhereFieldNum() { return this.whereFieldNum; }
	public void setWhereFieldNum(int whereFieldNum) { this.whereFieldNum = whereFieldNum; }
	
	public int getMoney() { return this.money; }
	public void setMoney(int money) { this.money = money;}
	
	public List<Item> getAllItemList(){ return this.allItemList; }
	public void setAllItemList(List<Item> allItemList) { this.allItemList = allItemList; }
	
	
}
