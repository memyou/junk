package junk.system;

import java.util.Scanner;

import junk.field.Field;
import junk.field.FieldInfo;
import junk.life.Human;
import junk.life.Player;

//ゲームに必要なフィールド、人物などの生成用
public abstract class GameSystem {
	
	//ゲームフィールドの生成
	static Field[][] cleateField() {
		//フィールドの大本を生成
		Field[][] field = new FieldInfo[5][5];
		
		//5*5フィールドインスタンス生成
		int count = 1; //フィールドの区画No.
		
		for(int i = 0;i < field.length;i++) {
			for(int j = 0;j < field.length;j++) {
				field[i][j] = new FieldInfo(count);
				count++;
			}
		}
		return field;
	}
	
	//プレイヤーの生成
	static Human cleatePlayer(Scanner scName,Scanner scNum,Player pl){
		System.out.println("\nようこそ、労働者。");
		System.out.println("登録情報を確認します。");
		System.out.print("廃棄区画のご利用は初めてですか？ 1.はい 2.いいえ >>");
		int select = scNum.nextInt();
		switch(select){
		case 1:
			//新規プレイヤークリエイト
			newPlayer(scName,scNum,pl);
			break;
		case 2:
			//過去データロード
			break;
		default :
			System.out.println("初めからやり直してください。");
			break;
		}
		
		
		
		return pl;
	}
	
	//新規プレイヤーの作成
	static Human newPlayer(Scanner scName,Scanner scNum,Player pl) {
		System.out.print("\n初めてのご利用ですね。まずはお名前をご記入ください。 >>");
		String name = scName.nextLine();
		System.out.println("\nそれでは次に、貴方の特徴を申告してください。");
		System.out.println("※プレイヤーの特徴（体格）により、特定のイベントにおける評価が変動します。");
		System.out.print(" 1.小柄 2.平均的 3.大柄 >>");
		int bodyType = scNum.nextInt();
		switch(bodyType) {
		case 1:
		case 2:
		case 3:
			pl = new Player(name,bodyType);
			System.out.println("\nそれでは許可証を発行します。\n");
			System.out.println("***労働許可証***");
			System.out.println(pl);
			System.out.println("\nいってらっしゃいませ。良い労働を。");
			break;
		default :
			System.out.println("特徴は1～3の数字で指定してください。");
			break;
		}
		return pl;
	}
	
	//既存プレイヤーの利用
	static Player continuePlayer(Player pl) {
		
		
		
		System.out.println("おかえりなさい、労働者");
		
		
		return pl;
	}
	
	
}