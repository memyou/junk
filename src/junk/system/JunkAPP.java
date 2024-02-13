package junk.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;
import junk.life.Appraiser;
import junk.life.Begger;
import junk.life.Human;
import junk.life.Player;
import junk.life.Thief;

public class JunkAPP {

	public static void main(String[] args) {
		//変数宣言、インスタンス生成
		//スキャナー
		Scanner scName = new Scanner(System.in); //名前用
		Scanner scNum = new Scanner(System.in); //数字用
		int select = 0;
		
		//プレイヤー
		Player pl = null;
		
		//盗賊、物乞い
		Human human = null;
		
		//フィールドの生成
		Field[][] field = GameSystem.cleateField();
		
		//アイテムリスト
		List<Item> itemList = new ArrayList<Item>();
		
		//鑑定士の生成（一人）
		Appraiser appraiser = new Appraiser();
		
		//カウント用クラス
		Score score = new Score();
		
		
		
		//ゲームのルール説明
//		GameSystem.useKey();
//		System.out.println("");
		
		//ゲームの導入部分
//		FileSystem.explanation();
		
		//ゲーム開始
		pl = GameSystem.cleatePlayer(scName,scNum,pl,itemList);
		
		
		
		System.out.println("\n***廃棄区画入口***");
		while(true) {
			
			System.out.printf("\n―労働%s―\n",score.getCountDay() == 0 ? "初日" : (score.getCountDay() + "日目"));
			
			//活動限界を迎えた時の処理
			if(Score.MAX_DAY == score.getCountDay() && Score.MAX_TURN == score.getCountTurn()) {
				System.out.println("\nあなたは活動限界を迎えました。鑑定と売却を済ませ、受付にて退場処理を行ってください。");
				do {
					System.out.print("1.鑑定と売却 2.情報確認 3.労働者情報を保存 4.退場（ゲーム終了） >>");
					select = scNum.nextInt();
					if(0 >= select || select > 5) {
						System.out.println("選択肢は1～3を指定してください。");
					}
				}while(0 >= select || select > 5);
				
				switch(select) {
				case 1://鑑定売却
					pl.callAppraiser(appraiser,scNum,select,score);
					break;
				case 2://情報確認
					GameSystem.displayData(scNum,field,pl,select);
					break;
				case 3://データ保存
					System.out.println("\n情報を保存しました。");
					FileSystem.save(pl);
					break;
				case 4://終了
					System.out.println("\nお疲れ様でした。またの労働をお待ちしております。");
					//最終データ表示
					score.allScore(pl);
					return;
				}
			}else{
				
				
				//盗賊、物乞いが発生するかどうか
				int r = new Random().nextInt(5) + 1;
				switch(r) {
				case 1: //盗賊
					int bodyType = new Random().nextInt(3) + 1;
					human = new Thief(bodyType);
					score.encountEnemy(); //接敵計測
					VsNpcSystem.encountEnemy(select,scNum,pl,score,human);
					break;
				case 2: //物乞い
					human = new Begger();
					score.encountEnemy(); //接敵計測
					VsNpcSystem.encountEnemy(select,scNum,pl,score,human);
					break;
				default: //何もしない
					System.out.println("\n誰とも遭遇しませんでした。"); //※動作確認用
					break;
				}
				
				//プレイヤーの行動
				System.out.println("\n「何をしようか？」");
				do {
					System.out.print("1.採掘する 2.先へ進む 3.鑑定と売却 4.情報確認 5.仕事を休む 6.退場（ゲーム終了） >>");
					select = scNum.nextInt();
					if(0 >= select || select > 7) {
						System.out.println("選択肢は1～6を指定してください");
					}
				}while(0 >= select || select > 7) ;
				
				switch(select) {
				case 1:
					//発掘する
					pl.excavateItem(field,score);
					break;
				case 2:
					//先へ進む
					//現在位置の表示
					GameSystem.mapStatus(field,pl);
					pl.moveOn(scNum,field);
					//移動後位置の表示
					GameSystem.mapStatus(field,pl);
					break;
				case 3:
					//鑑定と売却
					pl.callAppraiser(appraiser,scNum,select,score);
					break;
				case 4:
					//情報確認
					pl.displayData(scNum,field,pl,select);
					break;
				case 5:
					//仕事を休む
					pl.restWork(scNum,select,score);
					break;
				case 6:
					//退場処理
					pl.endWork(scNum,select,score);
					break;
				}
			}	
			
			
		}
		
		
	}

	
	

}
