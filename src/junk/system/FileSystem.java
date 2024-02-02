package junk.system;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import junk.life.Player;

public class FileSystem {
	//マップ情報の表示
	
	
	//プレイヤーデータを書き込み
	static void seve(Gson gson,String path,Player pl) {
		
		
		
	}
	
	//プレイヤーデータを読み取り
	static Player continuePlayer(Gson gson,String path,Player pl) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))){
			String json = br.readLine();
			pl = gson.fromJson(json, Player.class);
		}catch(IOException e) {
			System.out.println("過去の労働データが存在しません。");;
		}
		
		System.out.println("登録情報を確認します。\n");
		System.out.println("***労働許可証***");
		System.out.println(pl);
		System.out.println("\nよい労働を。");
		
		return pl;
	}
	
	
	//アイテム買取一覧
	
	
	//アイテムデータを書き込み
	
	
	//アイテムデータを読み取り
}
