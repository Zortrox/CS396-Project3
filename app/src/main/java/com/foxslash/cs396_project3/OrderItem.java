/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;

//class to organize each item in the menu
public class OrderItem {
	private String mName = "";			//name of the item (or section)
	private int mQuantity = 0;			//quantity customer ordered
	private double mPrice = 0.0;		//price of the item
	private String mDesc = "";			//description of the item
	private boolean bSection = false;	//if this is a food item or a section heading

	public OrderItem(String name, double price) {
		mName = name;
		mPrice = price;
	}
	public OrderItem(String name, double price, String desc) {
		mName = name;
		mPrice = price;
		mDesc = desc;
	}
	//create a section
	public OrderItem(String section) {
		mName = section;
		bSection = true;
	}

	//getters/setters
	public String getName() {
		return mName;
	}

	public String getDesc() {
		return mDesc;
	}

	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}
	public int getQuantity() {
		return mQuantity;
	}

	public double getPrice() {
		return mPrice;
	}

	public boolean isSection() {
		return bSection;
	}
}
