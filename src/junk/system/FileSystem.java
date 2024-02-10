package junk.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import junk.item.Item;
import junk.life.Player;

public class FileSystem {
	
	//導入部分の表示
	static void explanation() {
		File file = new File("data/explanation.txt");

		String line = null;
		List<String> list = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))){
			while((line = br.readLine()) != null) {
				list.add(line);
			}
		}catch(IOException e) {
			;
		}
		for(String s : list) {
			System.out.print(s);
			GameSystem.pushEnterKey();
		}
		System.out.println("");
	}
	
	//プレイヤーデータを書き込み
	static void save(Player pl) {
		Gson gson = new Gson();
		String json = gson.toJson(pl);
		File file = new File("playerDeta.json");
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"))){
			bw.write(json);
		}catch(IOException e) {
			;
		}
		
	}
	
	//プレイヤーデータを読み取り
	static Player continuePlayer(Player pl,List<Item> itemList) {
		Gson gson = new Gson();
		File file = new File("playerDeta.json");
		
		if(file.exists()) {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))){
				String json = br.readLine();
				pl = gson.fromJson(json, Player.class);
			}catch(IOException e) {
				;
			}
			pl.setAllItemList(itemList);
			System.out.println("登録情報を確認します。");
			System.out.println("\n***労働許可証***");
			System.out.println(pl);
			System.out.println("\nよい労働を。");
		}else {
			System.out.println("\n登録情報はありません。");
			
		}
		return pl;
	}
	
	//フレーバーテキストの読み込みと表示
	public static void flavortxt(String filename) {
		File file = new File(filename);
		String line = null;
		List<String> list = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))){
			while((line = br.readLine()) != null){
				list.add(line);
			}
		}catch(IOException e) {
			;
		}
		
		for(String text : list) {
			System.out.print(text);
		}
		System.out.println("");
	}
	
	
	
	
}
