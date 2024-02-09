package junk.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;
import junk.life.Appraiser;
import junk.life.Human;
import junk.life.Player;

public class JunkAPP {

	public static void main(String[] args) {
		//変数宣言、インスタンス生成
		//スキャナー
		Scanner scName = new Scanner(System.in); //名前用
		Scanner scNum = new Scanner(System.in); //数字用
		int select = 0;
		
		//プレイヤー
		Player pl = null;
		
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
			System.out.println("\n―労働" + (score.getCountDay() + 1) + "日目―");
			
			if(Score.MAX_DAY == score.getCountDay()) {
				System.out.println("\nあなたは活動限界を迎えました。受付にて退場処理を行ってください。");
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
					
					break;
				case 4://終了
					System.out.println("お疲れ様でした。またの労働をお待ちしております。");
					//最終データ表示
					
					break;
				}
			}else{
				
				Human enemy = null;
				enemy = VsNpcSystem.newEnemy();
				if(enemy != null) {
					VsNpcSystem.encountEnemy(select,scNum,pl,score,enemy);
				}
				
				System.out.println("\n「何をしようか？」");
				do {
					System.out.print("1.発掘する 2.先へ進む 3.鑑定と売却 4.情報確認 5.仕事を休む 6.退場（ゲーム終了） >>");
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
