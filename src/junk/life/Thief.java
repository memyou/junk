package junk.life;

public class Thief extends Human{
	
	public Thief(int strength) {
		super("盗賊");
		super.setStrength(strength);
	}
	
	@Override
	public void status() {
		System.out.printf("名前；%S\n",super.getName());
		//来歴、短いフレーバーテキスト
		switch(super.getStrength()) {
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
