/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class FragMenu extends Fragment {
	ArrayList<String> listOrders = null;
	ArrayList<OrderItem> listMenu = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		listOrders = bundle.getStringArrayList("orders");

		//create menu
		listMenu.add(new OrderItem("APPETIZERS"));
		listMenu.add(new OrderItem("Fried Mozzarella", 7, "So cheesy, it doesn't really need a pun. Fresh, hand breaded"));
		listMenu.add(new OrderItem("Beer Battered Pickles", 5, "A Jedi told us these are the pickles you are looking for"));
		listMenu.add(new OrderItem("Crab Cakes", 10, "We promise not to tell Dr. Zoidberg. +$1.50 Grilled avocado"));
		listMenu.add(new OrderItem("Spinach Artichoke Dip", 7, "Popeye’s favorite, served with warm tortilla chips"));
		listMenu.add(new OrderItem("Khaleesi's Caprese", 7, "Dine like a queen. Fresh mozzarella, basil, tomato, balsamic drizzle"));

		listMenu.add(new OrderItem("FRESH SALADS"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Caesar", 6, "A dressing so good, it has its own salad. Asiago, Romaine, croutons"));
		listMenu.add(new OrderItem("Curie's Cobb", 9, "Ham, egg, bacon, avocado, cheddar, tomato, croutons"));
		listMenu.add(new OrderItem("Baron Rutherford", 8, "Walnuts, apples, blue cheese crumbles, raspberry vinaigrette"));

		listMenu.add(new OrderItem("HOT SOUPS"));
		listMenu.add(new OrderItem("Red Pepper Bisque (Cup)", 3, "An eruption of savory flavors made in-house daily"));
		listMenu.add(new OrderItem("Seafood Bisque (Cup)", 3, "An eruption of savory flavors made in-house daily"));
		listMenu.add(new OrderItem("Seasonal (Cup)", 3, "An eruption of savory flavors made in-house daily"));
		listMenu.add(new OrderItem("Red Pepper Bisque (Bowl)", 5, "An eruption of savory flavors made in-house daily"));
		listMenu.add(new OrderItem("Seafood Bisque (Bowl)", 5, "An eruption of savory flavors made in-house daily"));
		listMenu.add(new OrderItem("Seasonal (Bowl)", 5, "An eruption of savory flavors made in-house daily"));

		listMenu.add(new OrderItem("WRIGHT BROS. BONELESS"));
		listMenu.add(new OrderItem("Bird Dog Bourbon BBQ (Wings)", 7, "One bite and you’ll see why these are “Wright” for you. Served with house-made ranch or blue cheese"));
		listMenu.add(new OrderItem("Buffalo (Wings)", 7, "One bite and you’ll see why these are “Wright” for you. Served with house-made ranch or blue cheese"));
		listMenu.add(new OrderItem("Sweet Thai Chili (Wings)", 7, "One bite and you’ll see why these are “Wright” for you. Served with house-made ranch or blue cheese"));
		listMenu.add(new OrderItem("Bird Dog Bourbon BBQ (Tenders)", 9, "One bite and you’ll see why these are “Wright” for you. Served with house-made ranch or blue cheese"));
		listMenu.add(new OrderItem("Buffalo (Tenders)", 9, "One bite and you’ll see why these are “Wright” for you. Served with house-made ranch or blue cheese"));
		listMenu.add(new OrderItem("Sweet Thai Chili (Tenders)", 9, "One bite and you’ll see why these are “Wright” for you. Served with house-made ranch or blue cheese"));

		listMenu.add(new OrderItem("BRILLIANT BURGERS"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));

		listMenu.add(new OrderItem("SAVORY SAMMICHES"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));

		listMenu.add(new OrderItem("WONDERFUL WRAPS"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));

		listMenu.add(new OrderItem("FLATBREAD PERSONAL PIZZAS"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));

		listMenu.add(new OrderItem("SIGNATURE ENTREES"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));
		listMenu.add(new OrderItem("Patent House", 6, "Our signature salad, lettuce, cucumber, cheese, tomato, croutons"));

		//get quantity for each item
		for (int i = 0; i < listOrders.size(); i++) {
			for (int j = 0; j < listMenu.size(); j++) {
				if (!listMenu.get(j).isSection()) {
					String name = listOrders.get(i).substring(0, listOrders.get(i).indexOf('$'));
					if (listMenu.get(j).getName().equals(name)) {
						listMenu.get(j).setQuantity(listMenu.get(j).getQuantity() + 1);
					}
				}
			}
		}

		return inflater.inflate(R.layout.frag_menu, container, false);
	}
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		LinearLayout layout = (LinearLayout) view.findViewById(R.id.menu_items);
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		for (int i = 0; i < listMenu.size(); i++) {
			final OrderItem tempItem = listMenu.get(i);

			if (tempItem.isSection()) {
				//Section heading
				final View child = inflater.inflate(R.layout.menu_header, layout, false);

				TextView txtName = (TextView) child.findViewById(R.id.text_heading);
				txtName.setText(tempItem.getName());

				layout.addView(child);
			} else {
				//Food item
				final View child = inflater.inflate(R.layout.menu_wrapper, layout, false);

				final TextView txtName = (TextView) child.findViewById(R.id.text_name);
				txtName.setText(tempItem.getName());

				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				TextView txtPrice = (TextView) child.findViewById(R.id.text_price);
				txtPrice.setText(formatter.format(tempItem.getPrice()));

				final TextView txtQty = (TextView) child.findViewById(R.id.text_qty);
				txtQty.setText(String.valueOf(tempItem.getQuantity()));

				ImageButton btnAdd = (ImageButton) child.findViewById(R.id.add_food);
				btnAdd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int qty = tempItem.getQuantity();
						if (qty < 99) {
							tempItem.setQuantity(tempItem.getQuantity() + 1);
							txtQty.setText(String.valueOf(tempItem.getQuantity()));
						}
					}
				});

				ImageButton btnSub = (ImageButton) child.findViewById(R.id.sub_food);
				btnSub.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int qty = tempItem.getQuantity();
						if (qty > 0) {
							tempItem.setQuantity(qty - 1);
							txtQty.setText(String.valueOf(tempItem.getQuantity()));
						}
					}
				});

				txtQty.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {

					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (s.toString().equals("")) {
							tempItem.setQuantity(0);
						} else {
							int qty = Integer.valueOf(s.toString());
							if (qty >= 0 && qty <= 99) tempItem.setQuantity(qty);
							else if (qty > 99) tempItem.setQuantity(99);
							else tempItem.setQuantity(0);
						}
					}

					@Override
					public void afterTextChanged(Editable s) {

					}
				});

				RelativeLayout descBox = (RelativeLayout) child.findViewById(R.id.click_desc);

				descBox.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						LayoutInflater inflater = LayoutInflater.from(getActivity());
						final View dialogView = inflater.inflate(R.layout.dialog_view_desc, null);

						builder.setView(dialogView);

						String name = txtName.getText().toString();
						String desc = "";
						for (int i = 0; i < listMenu.size(); i++) {
							if (name.equals(listMenu.get(i).getName())) {
								desc = listMenu.get(i).getDesc();
							}
						}

						((TextView) dialogView.findViewById(R.id.food_name)).setText(name);
						((TextView) dialogView.findViewById(R.id.food_desc)).setText(desc);

						builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

						builder.create().show();
					}
				});

				layout.addView(child);
			}
		}
	}

	public ArrayList<String> getOrder() {
		//remake list of items ordered
		listOrders.clear();
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		for (int i = 0; i < listMenu.size(); i++) {
			if (listMenu.get(i).getQuantity() > 0 && !listMenu.get(i).isSection()) {
				String name = listMenu.get(i).getName();
				String price = formatter.format(listMenu.get(i).getPrice());
				String item = name + price;
				for (int j = 0; j < listMenu.get(i).getQuantity(); j++) {
					listOrders.add(item);
				}
			}
		}

		return listOrders;
	}
}
