/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;


public class OrderItem {
	private String mName = "";
	private int mQuanity = 0;
	private double mPrice = 0.0;

	public OrderItem(String name, double price) {
		mName = name;
		mQuanity = 1;
		mPrice = price;
	}

	public String getName() {
		return mName;
	}

	public void setQuantity(int quantity) {
		mQuanity = quantity;
	}
	public int getQuantity() {
		return mQuanity;
	}

	public double getPrice() {
		return mPrice;
	}
}
