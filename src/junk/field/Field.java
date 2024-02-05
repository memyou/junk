package junk.field;

import junk.event.Event;
import junk.item.Item;

//フィールド座標、発生するイベント、アイテム埋蔵の情報
public class Field{
	public final static int AREA = 5;
	public final static int PIECES = AREA * AREA;
	private int fieldNum;
	private Event event;
	private Item item;
	
	//コンストラクタで位置情報のみセットする
	public Field(int fieldNum) { this.fieldNum = fieldNum; }
	
	//イベントとアイテムをセット 
	public void setField(Event event,Item item) {
		this.event = event;
		this.item = item;
	}
	
	public void fieldInfo() {
		//現在地の保有情報
		System.out.printf("現在位置：%d地区\n",this.fieldNum);
		System.out.printf("\n");
	}
	
	public int getFieldNum() { return this.fieldNum; }
	
	public Event getEvent() {
		if(this.event == null) {
			System.out.println("何かが起こる気配はない。");
			return this.event;
		}else {
			return this.event;
		}
	}
	public void setEvent(Event event) { this.event = event; }
	
	public Item getItem() { return this.item;}
	public void setItem(Item item) { this.item = item; }
	}
