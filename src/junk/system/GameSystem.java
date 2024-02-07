package junk.system;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import junk.event.EnemyEvent;
import junk.event.Event;
import junk.field.Field;
import junk.item.Accessory;
import junk.item.Born;
import junk.item.Category;
import junk.item.Item;
import junk.item.Machine;
import junk.item.Metal;
import junk.item.Wepon;
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
				Field fi = (Field)field[i][j];
				fi.setItem(new Item());
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
		System.out.println("※▼が出てきたらエンターキーを打鍵してください。※");
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
		System.out.println("\n[現在位置]");
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
	
	//アイテムの名前と価格を設定：全鑑定処理
	public static void setItemStatus(Player pl,int select) {
		int list = pl.getAllItemList().size();
		Item item = null;
		
		//全部鑑定した時
		if(select == 0) {
			for(int i = 0;i < list;i++) {
				item = pl.getAllItemList().get(i);
				if(item.getIdentified() == false) {
					//アイテムの書き換え
					settingItem(pl,i);
				}
			}
		}else{ //選択したものだけ鑑定した時
			settingItem(pl,(select - 1));
			
		}
	}
	
	//アイテム名と価格の設定：中身
	static void settingItem(Player pl,int select) {
		Category category = setItemCategory();
		int rand = new Random().nextInt(2);
		
		if(category instanceof Wepon) {
			if(rand == 0) {
				pl.getAllItemList().get(select).identified("葬送の宝剣",5000,category);
			}else {
				pl.getAllItemList().get(select).identified("純華の盾",3000,category);
			}
		}else if(category instanceof Accessory) {
			if(rand == 0) {
				pl.getAllItemList().get(select).identified("第■期■■■王朝■■■■妃記念ネックレス",1500,category);
			}else {
				pl.getAllItemList().get(select).identified("ブティック・ともよのドレス",300,category);
			}
		}else if(category instanceof Machine) {
			if(rand == 0) {
				pl.getAllItemList().get(select).identified("■■社製純正パーツ",560,category);
			}else {
				pl.getAllItemList().get(select).identified("ネルグネジ",100,category);
			}
		}else if(category instanceof Metal) {
			if(rand == 0) {
				pl.getAllItemList().get(select).identified("■■■国金貨",8000,category);
			}else {
				pl.getAllItemList().get(select).identified("オルタ鉄塊",7600,category);
			}
		}else{
			if(rand == 0) {
				pl.getAllItemList().get(select).identified("海竜の骨",9000,category);
			}else {
				pl.getAllItemList().get(select).identified("人骨",1,category);
			}
		}
	}
	
	//どの種類のアイテムを生成するか
	static Category setItemCategory() {
		int randCategory = new Random().nextInt(5);
		Category category = null;
		
		switch(randCategory) {
		case 0: //武器
			category = new Wepon();
			break;
		case 1: //装飾品
			category = new Accessory();
			break;
		case 2: //機械
			category = new Machine();
			break;
		case 3: //貴金属
			category = new Metal();
			break;
		case 4: //骸
			category = new Born();
			break;
		}
		return category;
	}
	
	//情報確認、自分のステータス、アイテムの買取価格表、アイテムのフレーバーテキスト
	static void displayData(Scanner scNum,Player pl,int select) {
		while(true) {
			do {
				System.out.println("\n「何の情報を確かめようか？」");
				System.out.print("1.自分のステータス 2.発掘アイテム一覧 3.アイテムの買取価格表 4.アイテムの説明 5.戻る >>");
				select = scNum.nextInt();
				if(0 >= select || select > 6) {
					System.out.println("選択肢は1～4を入力してください。");
				}
			}while(0 >= select || select > 6);
			
			switch(select) {
			case 1:
				pl.showStatus();
				break;
			case 2:
				pl.haveItem();
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				return;
			}
		}
	}
	
	//接敵
	static void encountEnemy(int select,Scanner scNum,Player pl,Score score,Field fieldEvent) {
		EnemyEvent ee = null;
		Human enemy = null;
		
		score.encountEnemy();
		
		//EventがEnemyEventかの調査
		if(fieldEvent.getEvent() instanceof EnemyEvent) {
			ee = (EnemyEvent)fieldEvent.getEvent();
			enemy = ee.getEnemy();
		}
		
		//Eventの中身の敵が誰か調査
		if(enemy instanceof Thief) {
			//盗賊登場セリフ
			enemy = (Thief)ee.getEnemy();
			
		}else if(enemy instanceof Begger) {
			//物乞い登場セリフ
			enemy = (Begger)ee.getEnemy();
		}
		
		
		while(true) {
			do {
				System.out.printf("\n「%sと遭遇してしまった。どうしよう？」\n",ee.getEnemy().getName());
				System.out.print("1.戦う 2.逃げる 3.説得する 4.観察する >>");
				select = scNum.nextInt();
				if(0 >= select || select > 5) {
					System.out.println("選択肢は1～4を入力してください。");
				}
			}while(0 >= select || select > 5);
			
			switch(select) {
			case 1:
				pl.battle(enemy,score);
				break;
			case 2:
				pl.run(enemy,score);
				break;
			case 3:
				break;
			case 4:
				pl.observation(enemy);
				break;
			}
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	

	//エンターキーのみの入力用
	public static void pushEnterKey() {
		new Scanner(System.in).nextLine();
	}
}