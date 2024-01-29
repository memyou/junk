package junk.event;

import junk.item.Item;
import junk.life.Human;

//フィールド、発生するイベントとプレイヤーの位置、アイテム埋蔵の情報
public class Field {
	private Event event;
	private Human player;
	private Item item;
	
	public Field(Event event,Human player,Item item) {
		this.event = event;
		this.player = player;
		this.item = item;
	}
	
	public void setEvent(Event event) { this.event = event; }
	public Event getEvent() { return this.event; }
	
	public void setPlayer(Human player) { this.player = player; }
	public Human getPlayer() { return this.player; }
	
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
}
