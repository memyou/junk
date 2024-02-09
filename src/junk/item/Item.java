package junk.item;

import junk.life.Begger;

public class Item {
	private String name; //アイテム名
	private int price; //アイテムの価値
	private Rarity rarity; //アイテムのレアリティ
	private Category category; //アイテムの種類
	private boolean identified; //鑑定が済んでるか否か
	
	
	//コンストラクタ
	public Item() {
		this.identified = false;
		this.rarity = new Rarity();
	}
	
	public Item(Begger begger) {
		this.identified = false;
		this.rarity = new Rarity(begger);
	}
	
	public void identified(String name,int price,Category category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.identified = true;
	}
	
	//情報を表示する
	public void displayStatus() {
		if(this.identified == true) {
			System.out.printf("[名称]%S [レアリティ]%s\n",this.name,this.rarity.getRarity());
		}else {
			System.out.println("[名称]未鑑定のアイテム");
		}
	}
	
	public int salePrice() {
		int salePrice = (int)(this.price * rarity.salePrice());
		return salePrice;
	}
	
	//getter.setter
	public String getName() { return this.name; }
	
	public int getPrice() { return this.price; }
	
	public Category getCategory() { return this.category; }
	
	public boolean getIdentified() { return this.identified; }
}
