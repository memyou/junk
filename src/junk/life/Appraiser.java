package junk.life;

import java.util.Scanner;

import junk.system.FileSystem;
import junk.system.GameSystem;
import junk.system.Score;
import junk.system.VsNpcSystem;

public class Appraiser extends Human{
	
	public Appraiser() {
		super("鑑定士",3);
	}
	
	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		//フレーバーテキスト
		FileSystem.infoAppraiser();
	}
	
	//プレイヤーの呼び出しを受けた時
	public void salesTalk() {
		System.out.printf("『どうもどうも、労働者サン。』");
		System.out.println("\n『いつでもどこでも鑑定サービス、ラジアーパをご利用いただき、まことにありがとうございます。』");
	}
	
	//鑑定する
	public void appraisalItem(Player pl,Scanner scNum,int select) {
		System.out.println("『はいはい、鑑定でございますねえ。』");
		
		//鑑定処理呼び出し
		GameSystem.appraisalItem(pl,scNum,select);
		
		System.out.println("『他に何かございますか？』");
	}
	
	//買取
	public void saleItem(Player pl,Scanner scNum,Score score) {
		System.out.println("『買取サービスをご利用ですね。どのアイテムをお売りいただけます？』");
		
		//買取処理呼び出し
		GameSystem.saleItem(pl,this,scNum, score);
		
		System.out.println("『他のご要望はおありで？』");
	}
	
	//戦闘、鑑定士の勝利確定
	public void winner(Player pl) {
		System.out.println("『全く、どうしようもないお方ですねえ。』");
		
		//没収処理呼び出し
		VsNpcSystem.confiscation(pl);
		
		GameSystem.elapsed(); //時間経過表現
		System.out.println("『我々に武器を向けるとどうなるか、おわかりいただけましたね？』");
		System.out.println("『ふふ、次は良い取引が出来ると良いのですが。……それでは、良い労働を。』");
	}
	
	
	
}
