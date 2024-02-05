package junk.system;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import junk.event.EnemyEvent;
import junk.event.Event;
import junk.field.Field;
import junk.item.Item;
import junk.life.Begger;
import junk.life.Human;
import junk.life.Player;
import junk.life.Thief;

//ゲームに必要なフィールド、人物などの生成用
public abstract class GameSystem {
	
	//ゲームフィールドの生成
	static Field[][] cleateField() {
		//フィールドの大本を生成
		Field[][] field = new Field[Field.AREA][Field.AREA];
		
		//5*5フィールドインスタンス生成
		int count = 1; //フィールドの区画No.
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				field[i][j] = new Field(count);
				count++;
			}
		}
		
		//アイテム生成とセット
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				if(field[i][j] instanceof Field) {
					Field fi = (Field)field[i][j];
					fi.setItem(new Item());
				}
			}
		}
		
		//対人イベントの生成とセット
		Event enemyEvent = null;
		Human enemy = null;
		
		//イベント生成用乱数
		int r = (int)Math.random() * 10;
		
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				if(2 <= r && r >= 7) {
					//盗賊生成の処理
					int bodyType = new Random().nextInt(3) + 1;
					enemy = new Thief(bodyType);
					enemyEvent = new EnemyEvent(enemy);
					field[i][j].setEvent(enemyEvent);
				}else if(1 == r) {
					//物乞い生成の処理
					enemy = new Begger();
					enemyEvent = new EnemyEvent(enemy);
					field[i][j].setEvent(enemyEvent);
				}else {
					enemyEvent = new EnemyEvent(enemy);
					field[i][j].setEvent(enemyEvent);
				}
			}
		}
		
		return field;
	}
	
	
	
	//ゲームで利用する操作の説明
	static void useKey() {
		System.out.println("※このゲームでは文字、数字入力、エンターキーを使用します。※");
	}
	
	//プレイヤーの生成
	static Player cleatePlayer(Scanner scName,Scanner scNum,Player pl,List<Item> itemList){
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
			pl = newPlayer(scName,scNum,pl,itemList);
			break;
		case 2:
			//過去データロード
			pl = FileSystem.continuePlayer(pl,itemList);
			//プレイヤー初期位置
			int whereFieldNum = 1 + Field.AREA * (Field.AREA - 1);
			pl.setWhereFieldNum(whereFieldNum);
			break;
		}
		return pl;
	}
	
	//新規プレイヤーの作成
	static Player newPlayer(Scanner scName,Scanner scNum,Player pl,List<Item> itemList) {
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
			pl = new Player(name,bodyType,itemList);
			System.out.println("\nそれでは許可証を発行します。");
			System.out.println("\n***労働許可証***");
			System.out.println(pl);
			System.out.println("\nいってらっしゃいませ。良い労働を。");
			//プレイヤー初期位置
			int whereFieldNum = 1 + Field.AREA * (Field.AREA - 1);
			pl.setWhereFieldNum(whereFieldNum);
			break;
		}
		
		return pl;
	}
	
	
	//マップ内の自分の位置とアイテムの有無
	static void mapStatus(Field[][] field,Player pl) {
		System.out.println("\n現在位置を確認します。");
		String[][] map = new String[Field.AREA][Field.AREA];
		
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				if(field[i][j].getItem() == null) {
					map[i][j] = "□";
				}else {
					map[i][j] = "■";
				}
				if(pl.getWhereFieldNum() == field[i][j].getFieldNum()) {
					map[i][j] = "◎";
				}
			}
		}
		for(String[] s : map) {
			for(String t : s) {
				System.out.print(t);
			}
			System.out.println("");
		}
	}
	
	
}