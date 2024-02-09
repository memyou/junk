package junk.system;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import junk.field.Field;
import junk.item.Accessory;
import junk.item.Born;
import junk.item.Category;
import junk.item.Item;
import junk.item.Machine;
import junk.item.Metal;
import junk.item.Wepon;
import junk.life.Appraiser;
import junk.life.Player;

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
	public static void mapStatus(Field[][] field,Player pl) {
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
	public static void displayData(Scanner scNum,Field[][] field,Player pl,int select) {
		while(true) {
			do {
				System.out.println("\n何の情報を確かめますか？");
				System.out.print("1.自分のステータス 2.現在位置確認 3.発掘アイテム一覧 4.アイテムの買取価格表 5.アイテムの説明 6.戻る >>");
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
				pl.whereNow(field,pl);
				break;
			case 3:
				pl.haveItem();
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				System.out.println("情報確認を終了します。");
				return;
			}
		}
	}
	
	//鑑定と売却
	public static void appraisal(Player pl,Appraiser appraiser,Scanner scNum,int select,Score score) {
		//登場時セリフ
		appraiser.salesTalk();
		
		do {
			System.out.println("\n鑑定士に対して何を行いますか？");
			System.out.print("1.鑑定 2.売却 3.観察する 4.襲撃する>>");
			select = scNum.nextInt();
			if(0 >= select || select > 5) {
				System.out.println("選択肢は1～4を入力してください。");
			}
		}while(0 >= select || select > 5);
		
		switch(select) {
		case 1: //鑑定
			appraisalItem(pl,scNum,select);
			break;
		case 2: //売却
			pl.saleItem(appraiser,scNum,score);
			break;
		case 3: //観察
			pl.observation(appraiser);
			break;
		case 4: //襲撃
			VsNpcSystem.battle(pl,appraiser,score);
			break;
		}
	}
	
	
	//鑑定する
	public static void appraisalItem(Player pl,Scanner scNum,int select) {
		int allItem = pl.getAllItemList().size();
		
		if(allItem == 0) {
			System.out.println("『さすがの我々も、現物がなければ鑑定できませんよ。』");
		}else {
				System.out.println("\n『鑑定サービスをご利用ですね。どのアイテムを鑑定致しますか？』");
				pl.haveItem();
				do {
					System.out.printf("\n0.全ての未鑑定アイテムを鑑定する 1～%d.未鑑定アイテムを個別に鑑定する >>",allItem);
					select = scNum.nextInt();
					
					if(0 > select || select > (allItem + 1)) {
						System.out.printf("アイテムは1～%dから選択してください。\n",(allItem + 1));
					}
					if(select != 0) {
						if(pl.getAllItemList().get(select - 1).getIdentified() == true) {
							System.out.println("『おやおや、これは鑑定済みですねえ。』");
						}
					}
				}while(0 > allItem || select > (allItem + 1));
				GameSystem.setItemStatus(pl,select);
				if(pl.getAllItemList().get(select - 1).getIdentified() != true) {
					System.out.println("『それでは鑑定致しましょう。』");
					for(int i = 0;i < 3;i++) {
						GameSystem.elapsed();
					}
					System.out.println("『これはこれは……はい、鑑定終了でございます。』");
				}
		}
	}
	
	//鑑定士にアイテムを売る
	public static void saleItem(Player pl,Appraiser appraiser,Scanner scNum,Score score) {
		int select = 0; //選択肢用
		Item item = null; //アイテムの鑑定を判定する用
		Item saleItem = null; //売却アイテムの一時避難用
		int allItem = pl.getAllItemList().size(); //所持アイテム総数
		int salePrice = 0; //売却時の実際の金額
		
		//鑑定士のセリフ
		appraiser.saleItem();
		
		//売却処理
		pl.haveItem();
		do {
			System.out.printf("0.全ての鑑定済みアイテムを売却する 1～%d.鑑定済みアイテムを個別に売却する",allItem);
			select = scNum.nextInt();
			if(0 > select || select > (allItem + 1)) {
				System.out.printf("選択肢は0～%dを入力してください。");
			}
			if(select > 1) {
				item = pl.getAllItemList().get((select - 1));
				if(item.getIdentified() == false) {
					System.out.println("これは未鑑定のアイテムです。売却には鑑定済みのアイテムを選択してください。");
				}
			}
			
		}while(0 > select || select > (allItem + 1));
		
		if(select == 0) {
			System.out.println("\n全ての鑑定済みアイテムを売却します。");
			for(int i = 0;i < allItem;i++) {
				item = pl.getAllItemList().get(i);
				if(item.getIdentified() == true) {
					score.saleItem();
					saleItem = pl.getAllItemList().remove(i);
					salePrice = saleItem.salePrice();
					pl.income(salePrice);
				}
			}
			System.out.println("全ての鑑定済みアイテムを売却しました。");
			pl.haveItem();
		}else {
			System.out.println("\n選択したアイテムのみ売却します。");
			score.saleItem();
			saleItem = pl.getAllItemList().remove((select - 1));
			salePrice = saleItem.salePrice();
			pl.income(salePrice);
		}
	}
	
	//仕事を一回休む
	public static void restWork(Scanner scNum,int select,Score score) {
		System.out.println("「\n発掘作業を一回休もうか？」");
		do {
			System.out.print("休みを取ると発掘回数を１消費します。休みますか？ 1.はい 2.いいえ >>");
			select = scNum.nextInt();
			if(0 >= select || select > 3) {
				System.out.println("選択肢は1または2です。");
			}
		}while(0 >= select || select > 3);
		switch(select) {
		case 1:
			score.countTurn();
			System.out.println("\n発掘作業をしませんでした。１日の残り採掘回数：" + (Score.MAX_TURN - score.getCoutnTurn()));
			if(score.getCountDay() == Score.MAX_DAY && score.getCoutnTurn() == Score.MAX_TURN) {
				System.out.println("「今回の仕事はここまでだ。換金して帰ろう。」");
			}
			break;
		case 2:
			System.out.println("「やっぱり作業をしよう。」");
			break;
		}
	}

	
	//現在位置とマップの位置を一致させるだけの処理
	public static Field now(Field[][] field,Player pl) {
		Field fieldNow = null;
		
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++) {
				if(field[i][j].getFieldNum() == pl.getWhereFieldNum()) {
					fieldNow = field[i][j];
				}
			}
		}
		return fieldNow;
	}
	
	
	
	
	

	//エンターキーのみの入力用
	public static void pushEnterKey() {
		new Scanner(System.in).nextLine();
	}
	
	//時間経過表現
	public static void elapsed() {
		for(int i = 0;i < 3;i++) {
			System.out.println("：▼");
			pushEnterKey();
		}
	}
}