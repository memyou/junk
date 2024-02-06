package junk.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;
import junk.life.Appraiser;
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
		
		
		
		
		
		//ゲームのルール説明
		GameSystem.useKey();
		System.out.println("");
		
		//ゲームの導入部分
		FileSystem.explanation();
		
		//ゲーム開始
		pl = GameSystem.cleatePlayer(scName,scNum,pl,itemList);
		System.out.println("\n***廃棄区画入口***");
		while(true) {
			System.out.println("\n労働" + pl.getTurn() + "日目");
			
			if(Player.TURN == pl.getTurn()) {
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
					pl.callAppraiser(appraiser,scNum,select);
					break;
				case 2://情報確認
					GameSystem.displayData(scNum,pl,select);
					break;
				case 3://データ保存
					
					break;
				case 4://終了
					System.out.println("お疲れ様でした。またの労働をお待ちしております。");
					//最終データ表示
					break;
				}
			}else {
				System.out.println("\n「何をしようか？」");
				do {
					System.out.print("1.発掘する 2.先へ進む 3.鑑定と売却 4.情報確認 5.仕事をやめる >>");
					select = scNum.nextInt();
					if(0 >= select || select > 6) {
						System.out.println("選択肢は1～5を指定してください");
					}
				}while(0 >= select || select > 6) ;
				
				switch(select) {
				case 1:
					//発掘する
					pl.excavateItem(field);
					break;
				case 2:
					//先へ進む
					GameSystem.mapStatus(field, pl);
					pl.moveOn(scNum,field);
					break;
				case 3:
					//鑑定と売却
					pl.callAppraiser(appraiser,scNum,select);
					break;
				case 4:
					//情報確認
					GameSystem.displayData(scNum,pl,select);
					break;
				case 5:
					//仕事をやめる
					pl.endWork(scNum);
					break;			
				}
			}	
			
			
		}
		
		
	}

	
	

}
