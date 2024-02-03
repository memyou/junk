package junk.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Item;
import junk.life.Player;

public class JunkAPP {

	public static void main(String[] args) {
		//変数宣言
		//スキャナー
		Scanner scName = new Scanner(System.in); //名前用
		Scanner scNum = new Scanner(System.in); //数字用
		int select = 0;
		
		//プレイヤー
		Player pl = null;
		
		//フィールドの生成
		Field[][] field = GameSystem.cleateField();
		
		//アイテムリスト
		List<Item> list = new ArrayList<Item>();
		
		//ターンのカウント用
		int turncount = 0;
		final int TURN = 5;
		
		
		
		//ゲームのルール説明
		GameSystem.useKey();
		System.out.println("");
		FileSystem.explanation();
		
		//ゲーム開始
		GameSystem.cleatePlayer(scName,scNum,pl);
		System.out.println("\n***廃棄区画入口***");
		while(true) {
			System.out.println("何をしようか？");
			do {
				System.out.print("1.先へ進む 2.発掘アイテム確認 3.所持金 4.仕事をやめる >>");
				select = scNum.nextInt();
				if(0 >= select || select > 5) {
					System.out.println("選択肢は1～4を指定してください");
				}
			}while(0 >= select || select > 5) ;
			
			switch(select) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			
			}
			
			
			
		}
		
		
	}

	
	

}
