package junk.system;

import java.util.Scanner;

import junk.field.Field;
import junk.field.FieldInfo;
import junk.item.Item;
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
		
		//アイテム生成とセット
		for(int i = 0;i < field.length;i++) {
			for(int j = 0;j < field.length;j++) {
				if(field[i][j] instanceof FieldInfo) {
					FieldInfo fi = (FieldInfo)field[i][j];
					fi.setItem(new Item());
				}
			}
		}
		return field;
	}
	
	//盗賊、物乞いイベントの生成
	public void enemyEvent(Field[][] field) {
		
	}
	
	
	
	
	//ゲームで利用する操作の説明
	static void useKey() {
		System.out.println("※このゲームでは文字、数字入力、エンターキーを使用します。※");
	}
	
	//プレイヤーの生成
	static Human cleatePlayer(Scanner scName,Scanner scNum,Player pl){
		int select = 0;
		System.out.println("\nようこそ、労働者。");
		System.out.println("登録情報を確認します。");
		
		do {
			System.out.print("\n廃棄区画のご利用は初めてですか？ 1.はい 2.いいえ >>");
			select = scNum.nextInt();
			if(0 >= select || select > 3) {
				System.out.println("選択肢は1または2を入力してください。");
			}
		}while(0 >= select || select > 3);
		switch(select){
		case 1:
			//新規プレイヤークリエイト
			newPlayer(scName,scNum,pl);
			break;
		case 2:
			//過去データロード
			FileSystem.continuePlayer(pl);
			break;
		}
		
		return pl;
	}
	
	//新規プレイヤーの作成
	static Human newPlayer(Scanner scName,Scanner scNum,Player pl) {
		String name = "";
		
		do {
			System.out.print("\n初めてのご利用ですね。まずはお名前をご記入ください。 >>");
			name = scName.nextLine();
			if(!(name.matches(".+"))) {
				System.out.println("名前は一文字以上で設定してください。");
			}
		}while(!(name.matches(".+")));
		
		System.out.println("\nそれでは次に、貴方の特徴を申告してください。");
		System.out.println("※プレイヤーの特徴（体格）により、特定のイベントにおける評価が変動します。");
		int bodyType = 0;
		do {
			System.out.print(" 1.小柄 2.平均的 3.大柄 >>");
			bodyType = scNum.nextInt();
			if(0 >= bodyType || bodyType > 4) {
				System.out.println("特徴は1～3の数字で指定してください。");
			}
		}while(0 >= bodyType || bodyType > 4) ;
		
		switch(bodyType) {
		case 1:
		case 2:
		case 3:
			pl = new Player(name,bodyType);
			System.out.println("\nそれでは許可証を発行します。");
			System.out.println("\n***労働許可証***");
			System.out.println(pl);
			System.out.println("\nいってらっしゃいませ。良い労働を。");
			break;
		}
		return pl;
	}
	
	
	//マップ内の自分の位置とアイテムの有無
	static void mapStatus(Field field,Player pl) {
		
	}
	
	
}