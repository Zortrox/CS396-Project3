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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View nowView = convertView;
		if (nowView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			nowView = inflater.inflate(R.layout.item_wrapper, null);
		}
		final View view = nowView;

		//layout
		RelativeLayout topLevel = (RelativeLayout)view.findViewById(R.id.item_box);
		topLevel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

			}
		});

		//Change item data
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		TextView listItemName = (TextView)view.findViewById(R.id.item_name);
		listItemName.setText(listOrders.get(position).getName());

		TextView listItemPrice = (TextView)view.findViewById(R.id.item_price);
		String strPrice = formatter.format(listOrders.get(position).getPrice());
		listItemPrice.setText(strPrice);

		TextView listItemQuantity = (TextView)view.findViewById(R.id.item_quantity);
		String strQuantity = "" + listOrders.get(position).getQuantity() + " x";
		listItemQuantity.setText(strQuantity);

		TextView listItemTotal = (TextView)view.findViewById(R.id.item_total);
		String strTotal = formatter.format(listOrders.get(position).getPrice() * listOrders.get(position).getQuantity());
		listItemTotal.setText(strTotal);

		return view;
	}
}
