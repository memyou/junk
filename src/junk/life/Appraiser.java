package junk.life;

import java.util.Scanner;

import junk.system.GameSystem;
import junk.system.Score;

public class Appraiser extends Human{
	
	public Appraiser() {
		super("鑑定士",3);
	}
	
	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		//フレーバーテキスト
		System.out.println("");
	}
	
	//鑑定と売却
	public void salesTalk(Player pl,Scanner scNum,int select,Score score) {
		System.out.printf("『どうもどうも、労働者%sサン。』",pl.getName());
		System.out.println("\n『いつでもどこでも鑑定サービス、ラジアーパをご利用いただき、まことにありがとうございます。』");
		
		do {
			System.out.println("『アイテムの鑑定と売却、どちらのサービスをご利用で？』");
			System.out.print("1.鑑定 2.売却 3.観察する 4.襲撃する>>");
			select = scNum.nextInt();
			if(0 >= select || select > 5) {
				System.out.println("『おっと、労働者サン。どうしました？』");
			}
		}while(0 >= select || select > 5);
		
		switch(select) {
		case 1: //鑑定
			appraisalItem(pl,scNum,select);
			break;
		case 2: //売却
			pl.saleItem(this,scNum,score);
			break;
		case 3: //観察
			pl.observation(this);
			break;
		case 4: //襲撃
			pl.battle(this,score);
			break;
		}
		
		
		
	}
	
	//鑑定する
	public void appraisalItem(Player pl,Scanner scNum,int select) {
		int allItem = pl.getAllItemList().size();
		
		if(allItem == 0) {
			System.out.println("『さすがの我々も、現物がなければ鑑定できませんよ。』");
		}else {
				System.out.println("\n『鑑定サービスをご利用ですね。どのアイテムを鑑定致しますか？』");
				pl.haveItem();
				do {
					System.out.printf("\n0.全ての未鑑定アイテムを鑑定する 1～%d.未鑑定アイテムを個別に鑑定する >>",allItem);
					select = scNum.nextInt();
					
					if(0 > select || select > (allItem + 1)) {
						System.out.printf("アイテムは1～%dから選択してください。\n",(allItem + 1));
					}
					if(select != 0) {
						if(pl.getAllItemList().get(select - 1).getIdentified() == true) {
							System.out.println("『おやおや、これは鑑定済みですねえ。』");
						}
					}
				}while(0 > allItem || select > (allItem + 1));
				GameSystem.setItemStatus(pl,select);
				if(pl.getAllItemList().get(select - 1).getIdentified() != true) {
					System.out.println("『それでは鑑定致しましょう。』");
					for(int i = 0;i < 3;i++) {
						GameSystem.pushEnterKey();
					}
					System.out.println("『これはこれは……はい、鑑定終了でございます。』");
				}
		}
	}
	
	//買取
	public void saleItem() {
		System.out.println("『買取サービスをご利用ですね。どのアイテムをお売りいただけます？』");
	}
	
}
