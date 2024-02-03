package junk.system;

import java.util.Scanner;

import junk.field.Field;
import junk.field.FieldInfo;
import junk.life.Player;

//プレイヤーの行動
public abstract class PlayerAction {	
	//先へ進む
	static void moveOn(Scanner scNum,Field[][] field,Player pl) {
		final int EAST = 1,WEST = 2,SOUTH = 3,NORTH = 4;
		
		System.out.println("\n「先へ進もう」");
		for(int i = 0;i < Field.AREA;i++) {
			for(int j = 0;j < Field.AREA;j++){
				if(field[i][j] instanceof FieldInfo) {
					FieldInfo fi = (FieldInfo)field[i][j];
					
					
					
					
					
					
				}
			}
		}
		
		
		
	}
	
	
	
	
	
	//アイテム確認
	
	//鑑定と売却
	
	//所持金確認
	
	//現在位置確認
	
	//仕事をやめる
	
}
