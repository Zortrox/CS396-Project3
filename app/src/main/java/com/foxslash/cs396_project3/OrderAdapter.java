/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

//adapter to add custom layout to customer order listview
public class OrderAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private ArrayList<OrderItem> listOrders = null;

	public OrderAdapter(ArrayList<OrderItem> list, Context context) {
		listOrders = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listOrders.size();
	}

	@Override
	public Object getItem(int pos) {
		return listOrders.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		//return list.get(pos).getId();
		return 0;
	}

	//create custom layout
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View nowView = convertView;
		if (nowView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			nowView = inflater.inflate(R.layout.item_wrapper, null);
		}
		final View view = nowView;

		//create currency formatter
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		//set new food item name
		TextView listItemName = (TextView)view.findViewById(R.id.item_name);
		listItemName.setText(listOrders.get(position).getName());

		//set new food item price
		TextView listItemPrice = (TextView)view.findViewById(R.id.item_price);
		String strPrice = formatter.format(listOrders.get(position).getPrice());
		listItemPrice.setText(strPrice);

		//set new quantity ordered
		TextView listItemQuantity = (TextView)view.findViewById(R.id.item_quantity);
		String strQuantity = String.valueOf(listOrders.get(position).getQuantity());
		listItemQuantity.setText(strQuantity);

		//set total cost for item (quantity * price)
		TextView listItemTotal = (TextView)view.findViewById(R.id.item_total);
		String strTotal = formatter.format(listOrders.get(position).getPrice() * listOrders.get(position).getQuantity());
		listItemTotal.setText(strTotal);

		return view;
	}

	//remove an item from the listview
	public void removeItem(int position) {
		listOrders.remove(position);
		notifyDataSetChanged();
	}
}
