package junk.field;

import junk.event.Event;
import junk.item.Item;

//フィールド座標、発生するイベント、アイテム埋蔵の情報
public class FieldInfo implements Field{
	private int fieldNum;
	private Event event;
	private Item item;
	
	//コンストラクタで位置情報のみセットする
	public FieldInfo(int fieldNum) { this.fieldNum = fieldNum; }
	
	//イベントとアイテムをセット
	public void setField(Event event,Item item) {
		this.event = event;
		this.item = item;
	}
	
	public int getFieldNum() { return this.fieldNum; }
	
	public void setEvent(Event event) { this.event = event; }
	public Event getEvent() {
		if(this.event == null) {
			System.out.println("何かが起こる気配はない");
			return this.event;
		}else {
			return this.event;
		}
	}
	
	public void setItem(Item item) {
		if(this.item == null) {
			this.item = item;
		}
	}
	public Item getItem() {
		if(this.item == null) {
			System.out.println("ここにはもう何もないようだ");
			return this.item;
		}else {
			return this.item;
		}
	}

	@Override
	public void fieldInfo() {
		//現在地の保有情報
		System.out.printf("現在位置：%d地区\n",this.fieldNum);
		System.out.printf("\n");
	}
}
