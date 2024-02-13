package junk.system;

import junk.life.Player;

public class Score {
	//日数
	private int countDay;
	public static final int MAX_DAY = 3;
	
	//ターン数
	private int countTurn;
	public static final int MAX_TURN = 3;
	
	//鑑定士の呼び出しと鑑定数と襲撃数
	private int countCallAp;
	private int countAppraisal;
	private int battleAp;
	
	//接敵回数とバトル、勝敗、逃走、説得回数
	private int encountEnemy;
	private int battleEnemy;
	private int winBattle;
	private int loseBattle;
	private int drawBattle;
	private int runaway;
	private int canPersuade;
	
	//全発掘個数
	private int allDigItem;
	//売却総数
	private int saleItem;
	
	//スコア加算用メソッド
	public void countDay() { this.countDay++; }
	
	public void countTurn() { this.countTurn++; }
	
	public void countCallAp() { this.countCallAp++; }
	public void countAppraisal() { this.countAppraisal++; }
	public void battleAp() { this.battleAp++; }
	
	public void encountEnemy() { this.encountEnemy++; }
	public void battleEnemy() { this.battleEnemy++; }
	public void winBattle() { this.winBattle++; }
	public void loseBattle() { this.loseBattle++; }
	public void drawBattle() { this.drawBattle++; }
	public void runaway() { this.runaway++; }
	public void canPersuade() { this.canPersuade++; }
	
	public void allDigItem() { this.allDigItem++; }
	
	public void saleItem() { this.saleItem++; }
	
	//最後に表示する
	public void allScore(Player pl) {
		System.out.println("\n[今回の勤務内容]");
		System.out.println(pl);
		System.out.printf("活動日数：%d日\n",this.getCountDay());
		System.out.printf("最終収入：%dZ\n",pl.getMoney());
		System.out.printf("鑑定士を呼び出した回数：%d回\n",this.getCountCallAp());
		System.out.printf("鑑定士を襲撃した回数：%d回\n",this.getBattleAp());
		System.out.printf("接敵（盗賊、物乞い）回数：%d回\n",this.getEncountEnemy());
		System.out.printf("戦闘回数（盗賊、物乞い、鑑定士）：%d回\n",this.getBattleEnemy());
		System.out.printf("戦闘に勝利した回数：%d回\n",this.getWinBattle());
		System.out.printf("戦闘に敗北した回数：%d回\n",this.getLoseBattle());
		System.out.printf("戦闘で引き分けた回数：%d回\n",this.getDrawBattle());
		System.out.printf("敵前逃亡した回数：%d回\n",this.getRunaway());
		System.out.printf("説得に成功した回数：%d回\n",this.getCanPersuade());
		System.out.printf("アイテムを発掘した総回数：%d回\n",this.getAllDigItem());
		System.out.printf("アイテムを売却した総個数：%d回\n",this.getSaleItem());
		
	}
	
	
	//getter,setter
	public int getCountDay() { return this.countDay; }
	
	public int getCountTurn() { return this.countTurn; }
	public void setCountTurn() { this.countTurn = 0;}
	
	public int getCountCallAp() { return this.countCallAp; }
	public int getCountAppraisal() { return this.countAppraisal; }
	public int getBattleAp() { return this.battleAp; }
	
	public int getEncountEnemy() { return this.encountEnemy; }
	public int getBattleEnemy() { return this.battleEnemy; }
	public int getWinBattle() { return this.winBattle; }
	public int getLoseBattle() { return this.loseBattle; }
	public int getDrawBattle() { return this.drawBattle; }
	public int getRunaway() { return this.runaway; }
	public int getCanPersuade() { return this.canPersuade; }
	
	public int getAllDigItem() { return this.allDigItem; }
	
	public int getSaleItem() { return this.saleItem; }
}
