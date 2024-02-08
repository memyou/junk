package junk.life;

public class Begger extends Human{
	
	
	public Begger() {
		super("物乞い",1);
	}

	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		//フレーバーテキスト
		System.out.println("");
		
	}
	
}
