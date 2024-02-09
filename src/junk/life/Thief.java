package junk.life;

import junk.system.VsNpcSystem;

public class Thief extends Worker{
	public static final int MAX_RUB = 3;
	
	public Thief(int bodyType) {
		super("盗賊",bodyType);
	}
	
	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		//来歴、短いフレーバーテキスト
		switch(super.getBodyType()) {
		case 0:
			//やせ型の盗賊
			System.out.println("細身の盗賊。");
			break;
		case 1:
			//普通体系の盗賊
			System.out.println("標準体型の盗賊。");
			break;
		case 2:
			//体格のいい盗賊
			System.out.println("体の大きな盗賊。");
			break;
		}
	}
	
	//登場セリフ
	public void encountTxt() {
		System.out.println("『おいてめえ！　出すもん出せやぁ！』");
	}
	
	//盗賊が勝利した時
	@Override
	public void winner(Human human) {
		if(human instanceof Player) {
			Player pl = (Player)human;
			System.out.println("『ハハハ、俺の勝ちだァ！　てめえのアイテムを頂いていくぜ！』");
			//強奪処理
			int rub = VsNpcSystem.rubItem(pl);
			rubItem(rub);
		}
	}
	
	//盗賊がアイテムを強奪した時の反応
	public void rubItem(int rub) {
		if(rub == MAX_RUB) {
			System.out.println("『おうおう、いいもん持ってんじゃねえか！　俺のためにありがとよ！』");
		}else if(rub > 0){
			System.out.println("『まあまあだな、なにもないよかマシだ』");
		}else {
			System.out.println("『おいおい、シケてんなあ！　チッ、骨折り損じゃねえか！』");
		}
	}
	
	//盗賊が敗北した時
	public void loser(Human human) {
		System.out.println("『くそ、覚えてろよ！』");		
	}
	
	//引き分けた時、アイテム変動なし
	@Override
	public void draw(Human human) {
		System.out.println("『チッ、今回は仕方ねえ。ずらかるか……。』");
	}
	
	@Override
	public void run(Human human) {
		System.out.println("『あっ、てめえ！　待ちやがれ――！』");
	}
	
	//盗賊を説得した時
	@Override
	public void persuade(Human human) {
		System.out.println("『……はあ、今回は見逃してやるよ』");
		
	}

	
	
	
}
