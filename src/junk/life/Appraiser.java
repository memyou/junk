package junk.life;

import java.util.Scanner;

public class Appraiser extends Human{
	
	public Appraiser() {
		super("鑑定士",3);
	}
	
	@Override
	public void showStatus() {
		System.out.printf("名前；%S\n",super.getName());
		//来歴、短いフレーバーテキスト
		System.out.println("");
	}
	
	//鑑定と売却
	public void salseTork(Player pl,Scanner scNum) {
		int select = 0;
		System.out.printf("『どうもどうも、労働者%sサン。』",pl.getName());
		System.out.println("\n『いつでもどこでも鑑定サービス、ラジアーパをご利用いただき、まことにありがとうございます。』");
		
		do {
			System.out.print("『アイテムの鑑定と売却、どちらのサービスをご利用で？』 1.鑑定 2.売却 3.観察する 4.襲撃する>>");
			select = scNum.nextInt();
			if(0 >= select || select > 5) {
				System.out.println("『おやおや、労働者サン。どうしました？』");
			}
		}while(0 >= select || select > 5);
		
		switch(select) {
		case 1: //鑑定
			break;
		case 2: //売却
			break;
		case 3: //観察
			break;
		case 4: //襲撃
			break;
		}
		
		
		
	}
	
	//鑑定する
	static void appraisalItem(Player pl,Scanner scNum,int select) {
		if(pl.getAllItemList().size() == 0) {
			System.out.println("『さすがの我々も、現物がなければ鑑定できませんよ。』");
		}else {
				System.out.println("\n『鑑定ですね。どのアイテムを鑑定しますか？』");
				pl.getAllItemList();
				do {
					System.out.printf("0.全ての未鑑定アイテムを鑑定する 1～.未鑑定アイテムを個別に鑑定する >>");
					select = scNum.nextInt();
					if(0 >= select || select > (pl.getAllItemList().size() + 1)) {
						System.out.println("アイテムは1～%dから選択してください。");
					}
					if(pl.getAllItemList().get(select - 1).getIdentified() == true) {
						System.out.println("『これは鑑定済みですねえ。』");
					}
				}while(0 >= pl.getAllItemList().size() || select > (pl.getAllItemList().size() + 1));
				if(select == 0) {
					for(int i = 0;i < pl.getAllItemList().size();i++) {
						if(pl.getAllItemList().get(i).getIdentified() == false) {
							//
						}
					}
				}
		}
	}
	
	//売却する
	public void saleItem(Player pl) {
		
	}
	
}
