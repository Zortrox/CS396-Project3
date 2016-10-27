/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragMenu extends Fragment {
	ArrayList<String> listOrders = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		listOrders = bundle.getStringArrayList("orders");

		return inflater.inflate(R.layout.frag_menu, container, false);
	}
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		final View fragView = view;

		Button button1 = (Button) view.findViewById(R.id.add_food_1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = ((TextView) fragView.findViewById(R.id.text_food_1)).getText().toString();
				String price = ((TextView) fragView.findViewById(R.id.text_price_1)).getText().toString();
				String item = name + price;
				listOrders.add(item);
			}
		});

		Button button2 = (Button) view.findViewById(R.id.add_food_2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = ((TextView) fragView.findViewById(R.id.text_food_2)).getText().toString();
				String price = ((TextView) fragView.findViewById(R.id.text_price_2)).getText().toString();
				String item = name + price;
				listOrders.add(item);
			}
		});

		Button button3 = (Button) view.findViewById(R.id.add_food_3);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = ((TextView) fragView.findViewById(R.id.text_food_3)).getText().toString();
				String price = ((TextView) fragView.findViewById(R.id.text_price_3)).getText().toString();
				String item = name + price;
				listOrders.add(item);
			}
		});

		Button button4 = (Button) view.findViewById(R.id.add_food_4);
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = ((TextView) fragView.findViewById(R.id.text_food_4)).getText().toString();
				String price = ((TextView) fragView.findViewById(R.id.text_price_4)).getText().toString();
				String item = name + price;
				listOrders.add(item);
			}
		});
	}

	public ArrayList<String> getOrder() {
		return listOrders;
	}
}
