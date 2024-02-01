package junk.item;

public class Rarity {
	private String rarity;
	private String rarityStatus;
	
	static final String[] RARITY = {
			"Legendary",
			"Rare",
			"Nomal",
			"Common",
			"Worthless"
	};
	static final String[] RARITYSTATUS = {
			"奇跡の",
			"貴重な",
			"汚れた",
			"壊れた",
			"無価値な"
	};
	
	public Rarity(int index) {
		this.rarity = RARITY[index];
		this.rarityStatus = RARITYSTATUS[index];
	}
	
	//getter,setter
	public String getRarity() { return this.rarity; }
	
	public String getRarityStatus() { return this.rarityStatus; }
	
}
