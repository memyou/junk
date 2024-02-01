package junk.system;

import java.util.Scanner;

import junk.field.Field;
import junk.field.FieldInfo;
import junk.life.Human;
import junk.life.Player;

//ゲームに必要なフィールド、人物などの生成用
public abstract class Cleate {
	
	//ゲームフィールドの生成
	static Field[][] cleateField() {
		//フィールドの大本を生成
		Field[][] field = new FieldInfo[5][5];
		
		//5*5フィールドインスタンス生成
		int count = 1; //フィールドの区画No.
		
		for(int i = 0;i < 5;i++) {
			for(int j = 0;j < 5;i++) {
				field[i][j] = new FieldInfo(count);
				count++;
			}
		}
		return field;
	}
	
	static Human cleatePlayer(Scanner scTxt,Scanner scNum,Player pl){
		System.out.println("ようこそ、労働者。");
		System.out.println("登録情報を確認します。");
		System.out.print("廃棄区画のご利用は初めてですか？ 1.はい 2.いいえ >>");
		int select = scNum.nextInt();
		switch(select){
		case 1:
			//新規プレイヤークリエイト
			break;
		case 2:
			//過去データロード
			break;
		default :
			System.out.println("初めからやり直してください");
			break;
		}
		
		
		
		return pl;
	}
	
	static Human newPlayer(Scanner scTxt,Player pl) {
		System.out.print("初めてのご利用ですね。まずはお名前をご記入ください >>");
		
		
		return pl;
	}
	
	
	
	
}