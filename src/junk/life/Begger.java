package junk.life;

public class Begger extends Human{
	
	
	public Begger() {
		super("物乞い",0);
	}

	@Override
	public void showStatus() {
		System.out.printf("名前：%S\n",super.getName());
		//フレーバーテキスト
		System.out.println("");
		
	}
	
	public void run() {
		System.out.println("『ああ、どうかお慈悲を……お待ちくだされ……』");
	}
	
	//負け確定
	public void loser() {
		System.out.println("『』");
	}
	
	//説得された時
	public void persuade() {
		System.out.println("『』");
	}
	
	
	
	
}
