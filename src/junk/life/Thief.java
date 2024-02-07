package junk.life;

public class Thief extends Human{
	
	public Thief(int bodyType) {
		super("盗賊",bodyType);
	}
	
	@Override
	public void showStatus() {
		System.out.printf("名前；%S\n",super.getName());
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
		
	}
	
	//盗賊が勝利した時、ランダムで手持ちアイテムを～３個奪われる
	public void winner(Player pl) {
		
	}
	
	//盗賊が敗北した時、ランダムで未鑑定アイテムを～３個入手する
	public void loser(Player pl) {
		
	}
	
	//盗賊を説得した時
	public void persuade(Player pl) {
		
	}

}
