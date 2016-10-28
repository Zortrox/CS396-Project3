/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;


public class OrderItem {
	private String mName = "";
	private int mQuantity = 0;
	private double mPrice = 0.0;
	private String mDesc = "";
	private boolean bSection = false;

	public OrderItem(String name, double price) {
		mName = name;
		mPrice = price;
	}
	public OrderItem(String name, double price, String desc) {
		mName = name;
		mPrice = price;
		mDesc = desc;
	}
	public OrderItem(String section) {
		mName = section;
		bSection = true;
	}

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
