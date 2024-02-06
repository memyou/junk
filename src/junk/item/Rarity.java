package junk.item;

import java.util.Random;

public class Rarity {
	private String rarity;
	private String rarityStatus;
	
	public static final String[] RARITY = {
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
	
	//コンストラクタ
	public Rarity() {
		//レアリティをランダム生成
		int randRarity = new Random().nextInt(100) + 1;
		int index;
		
		if(81 <= randRarity || randRarity <=100) {
			index = 4;
		}else if(46 <= randRarity || randRarity <= 80) {
			index = 3;
		}else if(16 <= randRarity || randRarity <= 45) {
			index = 2;
		}else if(6 <= randRarity || randRarity <= 15) {
			index = 1;
		}else {
			index = 0;;
		}
		
		this.rarity = RARITY[index];
		this.rarityStatus = RARITYSTATUS[index];
	}
	
	//レアリティによる価格の変動値
	public double salePrice() {
		double fluctuation = 0;
		if(this.rarity == Rarity.RARITY[0]) {
			fluctuation = 10;
		}else if(this.rarity == Rarity.RARITY[1]) {
			fluctuation = 3;
		}else if(this.rarity == Rarity.RARITY[2]) {
			fluctuation = 1;
		}else if(this.rarity == Rarity.RARITY[3]) {
			fluctuation = 0.8;
		}else {
			fluctuation = 0;
		}
		return fluctuation;
	}

	
	//getter,setter
	public String getRarity() { return this.rarity; }
	public String getRarityStatus() { return this.rarityStatus; }
	
}
