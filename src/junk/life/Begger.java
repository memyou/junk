package junk.life;

import junk.system.FileSystem;
import junk.system.VsNpcSystem;

public class Begger extends Human{
	
	
	public Begger() {
		super("物乞い",0);
	}

	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		//フレーバーテキスト呼び出し
		FileSystem.flavortxt("data/flavor_begger.txt");
	}
	
	//登場時セリフ
	public void encountTxt() {
		System.out.println("\n『そこの労働者様……私にどうか、お恵み頂けませんでしょうか……。』");
	}
	
	public void run() {
		System.out.println("『ああ、どうかお慈悲を……お待ちくだされ……！』");
	}
	
	//物乞いは負け確定
	public void loser() {
		System.out.println("『ひぃぃ、お助けぇ……！』");
	}
	
	//対話時
	public void persuade(Player pl) {
		if(pl.getMoney() == 0) {
			System.out.println("『そうでございましたか、無理を言って申し訳ありません……。』");
			System.out.println("『貴方の労働に幸あらんことを。それでは……。』");
		}else {
			System.out.println("『おお、なんと慈悲深い……ありがとうございます、労働者様……！』");
			System.out.println("『こちらをどうぞお受け取りください。』");
			
			VsNpcSystem.take(pl,this);
		}
	}
	
	
	
	
}
