package junk.life;

public class Appraiser extends Human{
	
	public Appraiser() {
		super("鑑定士",3);
	}
	
	@Override
	public void status() {
		System.out.printf("名前；%S\n",super.getName());
		//来歴、短いフレーバーテキスト
		System.out.println("");
	}

}
