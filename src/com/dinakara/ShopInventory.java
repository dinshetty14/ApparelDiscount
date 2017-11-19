package com.dinakara;

class ShopInventory {

	public ShopInventory(String[] item) {
		serialNum = Integer.parseInt(item[0].trim());
		brand = item[1].trim();
		apparel = item[2].trim();
		price = Float.parseFloat(item[3].trim());
	}
	private int serialNum;
    private String brand;
    private String apparel;
    private float price;
    private float discountedPrice;
    
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getApparel() {
		return apparel;
	}
	public void setApparel(String apparel) {
		this.apparel = apparel;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	@Override
	public String toString() {
		return "UserInput [serialNum=" + serialNum + ", brand=" + brand + ", apparel=" + apparel + ", price="
				+ price + "]";
	}
}
