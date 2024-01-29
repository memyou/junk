package junk.item;

public abstract class Item {
	private String name; //アイテム名
	private int price; //アイテムの価値
	private Rarity rarity; //アイテムのレアリティ
	private Category category; //アイテムの種類
	
	
	//コンストラクタ
	public Item(String name,Rarity rarity,Category category) {
		this.name = name;
		this.rarity = rarity;
		this.category = category;
	}
	
	//情報を表示する
	public void displayStatus() {
		System.out.printf("[名称]%S  [価値]%d\n",this.name,this.rarity.getRarity());
		System.out.printf("[価格]%d\n",this.price);
		System.out.printf("[種類]%S\n",this.category.getCategoryName());
	}
}
