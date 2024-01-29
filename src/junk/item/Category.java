package junk.item;

public abstract class Category {
	private String categoryName;
	
	//コンストラクタ
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
}
