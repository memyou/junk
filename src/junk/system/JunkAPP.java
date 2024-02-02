package junk.system;

import java.util.Scanner;

import com.google.gson.Gson;

import junk.field.Field;
import junk.life.Player;

public class JunkAPP {

	public static void main(String[] args) {
		//変数宣言
		//スキャナー
		Scanner scName = new Scanner(System.in); //名前用
		Scanner scNum = new Scanner(System.in); //数字用
		//ファイルの使用
		Gson gson = new Gson();
		String path = "playerDeta.json";
		
		//プレイヤー
		Player pl = null;
		
		
		
		//フィールドの生成
		Field[][] field = GameSystem.cleateField();
		
		
		//ゲーム開始
		GameSystem.cleatePlayer(scName,scNum,gson,path,pl);
		
		
		
		
	}

}
