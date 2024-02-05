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
		
		//確認用
		
		
		
		
		
		
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
				System.out.println("\n活動限界を迎えました。労働者は受付にて退場処理を行ってください。");
				do {
					System.out.print("1.鑑定と売却 2.所持金確認 3.労働者情報を保存 4.退場（ゲーム終了） >>");
					select = scNum.nextInt();
					if(0 >= select || select > 5) {
						System.out.println("選択肢は1～3を指定してください。");
					}
				}while(0 >= select || select > 5);
				
				switch(select) {
				case 1://鑑定売却
					break;
				case 2://所持金確認
					break;
				case 3://データ保存
					break;
				case 4://終了
					break;
				}
			}else {
				System.out.println("\n「何をしようか？」");
				do {
					System.out.print("1.発掘する 2.先へ進む 3.発掘アイテム確認 4.鑑定と売却 5.所持金確認 6.現在位置確認 7.仕事をやめる >>");
					select = scNum.nextInt();
					if(0 >= select || select > 8) {
						System.out.println("選択肢は1～6を指定してください");
					}
				}while(0 >= select || select > 8) ;
				
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
					//発掘アイテム確認
					pl.haveItem();
					break;
				case 4:
					//鑑定と売却
					break;
				case 5:
					//所持金
					pl.haveMoney();
					break;
				case 6:
					//現在位置確認
					GameSystem.mapStatus(field, pl);
					break;
				case 7:
					//仕事を辞める
					pl.endWork(scNum);
					break;			
				}
			}	
			
			
		}
		
		
	}

	
	

}
