package junk.life;

import junk.system.VsNpcSystem;

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
	
	//プレイヤーの呼び出しを受けた時
	public void salesTalk() {
		System.out.printf("『どうもどうも、労働者サン。』");
		System.out.println("\n『いつでもどこでも鑑定サービス、ラジアーパをご利用いただき、まことにありがとうございます。』");
	}
	
	//鑑定する
	public void appraisalItem() {
		
		
	}
	
	//買取
	public void saleItem() {
		System.out.println("『買取サービスをご利用ですね。どのアイテムをお売りいただけます？』");
	}
	
	//戦闘、鑑定士の勝利確定
	public void winner(Player pl) {
		System.out.println("『』");
		
		//処理呼び出し
		VsNpcSystem.confiscation(pl);
		
		System.out.println("『』");
	}
	
	
	
}
