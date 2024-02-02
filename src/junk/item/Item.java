package junk.item;

public class Item {
	private String name; //アイテム名
	private int price; //アイテムの価値
	private Rarity rarity; //アイテムのレアリティ
	private Category category; //アイテムの種類
	private boolean identified; //鑑定が済んでるか否か
	
	
	//コンストラクタ
	public Item() {
		boolean identified = false;
	}
	
	public void identified(String name,Rarity rarity,Category category) {
		this.name = name;
		this.rarity = rarity;
		this.category = category;
		this.identified = true;
	}
	
	//情報を表示する
	public void displayStatus() {
		if(this.identified == true) {
			System.out.printf("[名称]%S  [価値]%d\n",this.name,this.rarity.getRarity());
			System.out.printf("[価格]%d\n",this.price);
			System.out.printf("[種類]%S\n",this.category.getCategoryName());
		}else {
			System.out.printf("[名称]未鑑定の%s",this.category.getCategoryName());
		}
		
	}
}
