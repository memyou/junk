package junk;

public abstract class Item {
	private String name;
	private int price;
	private String rarity;
	private String category;
	private String text;
	
	//アイテムのレアリティ
	static final String[][] RARITY = {
			{"","","","","","",""},
			{"Epic","Legendary","Hyper rare","Rare","Nomal","Common","Worthless"}
	};
	
	
	
	
	
	public Item(String name,char rarity,String category,String text) {
		this.name = name;
		this.rarity = RARITY[RARITY.length - 1][rarity];
		this.category = category;
		this.text = text;
	}
	
	public void displayStatus() {
		System.out.printf("[名称]%S  [価値]%d\n",this.name,this.rarity);
		System.out.printf("[価格]%d\n",this.price);
		System.out.printf("[説明]\n%S : %S\n",this.category,this.text);
	}
}
