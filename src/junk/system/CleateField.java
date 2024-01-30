package junk.system;

import junk.field.Field;
import junk.field.FieldInfo;


public class CleateField {
	public void cleateField() {
		//フィールドの大本を生成
		Field[][] field = new FieldInfo[5][5];
		
		//5*5フィールドインスタンス生成
		int count = 0;
		
		for(int i = 0;i < 5;i++) {
			for(int j = 0;j < 5;i++) {
				field[i][j] = new FieldInfo(count);
				count++;
			}
		}
	}
	
}