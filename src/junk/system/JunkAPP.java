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
		
		//ターンのカウント用
		int turnCount = 0;
		final int TURN = 5;
		
		//鑑定士の生成（一人）
		Appraiser appraiser = new Appraiser();
		
		//確認用
		
		
		
		
		
		
		//ゲームのルール説明
		GameSystem.useKey();
		System.out.println("");
		
		//ゲームの導入部分
		FileSystem.explanation();
		
		//ゲーム開始
		GameSystem.cleatePlayer(scName,scNum,pl,itemList);
		System.out.println("\n***廃棄区画入口***");
		while(true) {
			System.out.println("何をしようか？");
			do {
				System.out.print("1.先へ進む 2.発掘アイテム確認 3.鑑定と売却 4.所持金 5.現在位置確認 6.仕事をやめる >>");
				select = scNum.nextInt();
				if(0 >= select || select > 7) {
					System.out.println("選択肢は1～6を指定してください");
				}
			}while(0 >= select || select > 7) ;
			
			switch(select) {
			case 1:
				//先へ進む
				
				break;
			case 2:
				//発掘アイテム確認
				break;
			case 3:
				//鑑定と売却
				break;
			case 4:
				//所持金
				break;
			case 5:
				//現在位置確認
				GameSystem.mapStatus(field, pl);
				break;
			case 6:
				//仕事を辞める
				break;			
			}
			
			
			
		}
		
		
	}

	
	

}
