package junk.life;

public class Thief extends Worker{
	
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
		System.out.println("『』");
	}
	
	//盗賊が勝利した時
	@Override
	public void winner() {
		System.out.println("『』");
	}
	
	//盗賊が敗北した時
	public void loser(Human human) {
		System.out.println("『』");
	}
	
	//引き分けた時、アイテム変動なし
	@Override
	public void draw() {
		System.out.println("『チッ、今回は仕方ねえ。ずらかるか……。』");
		
	}
	
	@Override
	public void run() {
		System.out.println("『あっ、てめえ！　待ちやがれ――！』");
	}
	
	//盗賊を説得した時
	@Override
	public void persuade(Human human) {
		
		
	}

	
	//インスタンスの内容を検査する
	@Override
	public Thief investigation(Human human) {
		Thief thief = null;
		if(human instanceof Thief) {
			thief = (Thief)human;
			return thief;
		}else {
			return thief;
		}
	}
	
}
