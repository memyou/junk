package junk.item;

public class Rarity {
	String rarity;
	String rarityStatus;
	
	static final String[] RARITY = {
			"Legendary",
			"Rare",
			"Nomal",
			"Common",
			"Worthless"
	};
	private final String[] RARITYSTATUS = {
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
	
	public String getRarity() {
		return this.rarity;
	}
	public String getRarityStatus() {
		return this.rarityStatus;
	}
}
