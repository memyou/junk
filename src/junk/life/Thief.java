package junk.life;

public class Thief extends Human{
	
	public Thief(int bodyType) {
		super("盗賊",bodyType);
	}
	
	@Override
	public void status() {
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

}
