package junk.life;

import java.util.List;

import junk.field.FieldInfo;
import junk.item.Item;

public class Player extends Human{
	private int whereFieldNum;
	private int money;
	private List<Item> itemList;
	
	//新規作成用コンストラクタ
	public Player(String name,int bodyType,List<Item> itemList) {
		super(name,bodyType);
		this.money = 0;
		this.itemList = itemList;
	}
	//コンテニュー用コンストラクタ
	public Player(String name,int bodyType,int money) {
		super(name,bodyType);
		this.money = money;
	}
	
	@Override
	public void status() {
		System.out.printf("名前；%S\n",super.getName());
		//来歴、短いフレーバーテキスト
		System.out.println("");
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
			//対盗賊
			
			//対物乞い
			
		}else {
			//確率で逃げられる
			
		}
	}
	
	//getter,setter
	public int getWhereFieldNum() { return this.whereFieldNum; }
	public void setWhereFieldNum(FieldInfo field) { this.whereFieldNum = field.getFieldNum(); }
	
	public int getMoney() { return this.money; }
	public void setMoney(int money) { this.money += money;}
	
	public List<Item> getItemList(){ return this.itemList; }
	public void setItemList(List<Item> item) { this.itemList = itemList; }
}
