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
	
	//鑑定する
	public void appraisal() {
		
	}
	
	//売却する
	public void saleItem(Player pl) {
		
	}
	
}
