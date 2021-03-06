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

//fragment to display all menu items and their headings
public class FragMenu extends Fragment {
	ArrayList<String> listOrders = null;
	ArrayList<OrderItem> listMenu = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//get order list argument to update quantities
		Bundle bundle = getArguments();
		listOrders = bundle.getStringArrayList("orders");

		//add menu items & headings
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
		listMenu.add(new OrderItem("Schrodinger", 8, "Both a classic cheeseburger and a burger with cheese at the same time"));
		listMenu.add(new OrderItem("Lovelace", 10, "Jalapeno slices, cheddar, beer battered onion ring, house-made Bird Dog Bourbon BBQ sauce"));
		listMenu.add(new OrderItem("Jobs", 10, "Not available on the app store: Avocado, bacon, egg, cheddar cheese, mayo"));
		listMenu.add(new OrderItem("Chakkapak", 11, "In honor of the world’s favorite sauce! Savory sriracha mayo, bacon, chipotle gouda, caramelized onion"));
		listMenu.add(new OrderItem("1-UP", 11, "Need an extra life? Mushroom, caramelized onion, tomato, swiss Go green, and we’ll hold the meat"));

		listMenu.add(new OrderItem("SAVORY SAMMICHES"));
		listMenu.add(new OrderItem("Bell's BLT", 8, "Practically calling your name. Bacon, lettuce, tomato, mayo"));
		listMenu.add(new OrderItem("Inventor's Club", 9, "Ham, turkey, bacon, cheddar cheese, lettuce, tomato, mayo"));
		listMenu.add(new OrderItem("E=m(cheese)²", 7, "Grilled cheese, pick two: American, cheddar, swiss chipotle gouda, pepper jack"));
		listMenu.add(new OrderItem("Kermit's Revenge", 10, "Bird Dog bourbon BBQ pulled pork with Asian inspired slaw"));
		listMenu.add(new OrderItem("Spaceballs", 10, "May the schwartz be with you. House-made meatballs, mozzarella"));

		listMenu.add(new OrderItem("WONDERFUL WRAPS"));
		listMenu.add(new OrderItem("Turing's Turkey", 9, "Programmed to solve your hunger. Turkey, lettuce, cheddar cheese, tomato, house made ranch"));
		listMenu.add(new OrderItem("Avocado & Bacon", 9, "Superfood + bacon = the perfect combo? Bacon, avocado, cheddar cheese, lettuce, house made ranch"));
		listMenu.add(new OrderItem("Teddy's Veggie", 9, "Named after Theophrastus, the OG vegetarian: Avocado, lettuce, tomato, red onion, green pepper, swiss, sriracha mayo"));

		listMenu.add(new OrderItem("PERSONAL PIZZAS"));
		listMenu.add(new OrderItem("Big Dipper", 9, "Predictably yummy bites of spinach artichoke dip & mozz"));
		listMenu.add(new OrderItem("Tatonka", 10, "Costner’s favorite: Fried chicken, buffalo sauce, ranch, mozzarella"));
		listMenu.add(new OrderItem("Pott's Popper", 9, "Jalapeno slices, spicy cheese sauce, bacon, mozzarella"));
		listMenu.add(new OrderItem("Eureka!", 10, "This grilled chicken & caesar pizza is worth its weight in gold"));

		listMenu.add(new OrderItem("SIGNATURE ENTREES"));
		listMenu.add(new OrderItem("Alfred's Alfredo", 12, "From the Wayne cookbook, add Chicken or Shrimp, Gotham in need? Go blackened"));
		listMenu.add(new OrderItem("Iron Lady", 14, "Delicous beer battered fish and chips, served with cole slaw"));
		listMenu.add(new OrderItem("Sir Isaac", 18, "Chicken fingers over brown sugar & bacon waffles with our own apple bourbon maple syrup"));
		listMenu.add(new OrderItem("String Theory", 16, "Spaghetti & house-made, never frozen meatballs with marinara"));
		listMenu.add(new OrderItem("The Other Tyson", 13, "Grilled chicken, pick a topping: BBQ, Raspberry, Parmesan, or Cheesy Jalapeno"));
		listMenu.add(new OrderItem("Surf n' Turf", 19, "Grilled grouper loaded with our signature crab stuffing"));
		listMenu.add(new OrderItem("Bourbon Salmon", 17, "Fresh, hand-cut, 7oz filet marinated in our secret Bird Dog Bourbon recipe"));

		//get quantity for each item
		//for when user edited order on order screen
		for (int i = 0; i < listOrders.size(); i++) {
			for (int j = 0; j < listMenu.size(); j++) {
				//if the menu item isn't a section heading
				if (!listMenu.get(j).isSection()) {
					//and the name is equal to an item already in the order
					String name = listOrders.get(i).substring(0, listOrders.get(i).indexOf('$'));
					if (listMenu.get(j).getName().equals(name)) {
						//increase the quantity
						listMenu.get(j).setQuantity(listMenu.get(j).getQuantity() + 1);
					}
				}
			}
		}

		return inflater.inflate(R.layout.frag_menu, container, false);
	}

	//inflate menu items and add listeners
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		//get linear layout from fragment xml
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.menu_items);
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		for (int i = 0; i < listMenu.size(); i++) {
			//get order item
			final OrderItem tempItem = listMenu.get(i);

			if (tempItem.isSection()) {
				//SECTION HEADING
				final View child = inflater.inflate(R.layout.menu_header, layout, false);

				//set heading name textview
				TextView txtName = (TextView) child.findViewById(R.id.text_heading);
				txtName.setText(tempItem.getName());

				//add to linear layout menu
				layout.addView(child);
			} else {
				//FOOD ITEM
				final View child = inflater.inflate(R.layout.menu_wrapper, layout, false);

				//set name for menu item
				final TextView txtName = (TextView) child.findViewById(R.id.text_name);
				txtName.setText(tempItem.getName());

				//set price for menu item
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				TextView txtPrice = (TextView) child.findViewById(R.id.text_price);
				txtPrice.setText(formatter.format(tempItem.getPrice()));

				//set quantity for menu item
				final TextView txtQty = (TextView) child.findViewById(R.id.text_qty);
				txtQty.setText(String.valueOf(tempItem.getQuantity()));

				//add click listener for button to increase quantity
				ImageButton btnAdd = (ImageButton) child.findViewById(R.id.add_food);
				btnAdd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//don't let quantity be greater than 99
						int qty = tempItem.getQuantity();
						if (qty < 99) {
							//update quantity in object and textview
							tempItem.setQuantity(tempItem.getQuantity() + 1);
							txtQty.setText(String.valueOf(tempItem.getQuantity()));
						}
					}
				});

				//add click listener for button to decrease quantity
				ImageButton btnSub = (ImageButton) child.findViewById(R.id.sub_food);
				btnSub.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//don't let quantity be less than 0
						int qty = tempItem.getQuantity();
						if (qty > 0) {
							//update quantity in object and textview
							tempItem.setQuantity(qty - 1);
							txtQty.setText(String.valueOf(tempItem.getQuantity()));
						}
					}
				});

				//add listener to quantity textview to update quantity for object
				txtQty.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {

					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (s.toString().equals("")) {
							//if quantity is blank, quantity is 0
							tempItem.setQuantity(0);
						} else {
							//update quantity and keep in bounds 0 <= qty <= 99
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

				//use layout for invisible "button"
				//set click listener to display food item description dialog
				RelativeLayout descBox = (RelativeLayout) child.findViewById(R.id.click_desc);
				descBox.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//new dialog builder
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						//inflate custom layout
						LayoutInflater inflater = LayoutInflater.from(getActivity());
						final View dialogView = inflater.inflate(R.layout.dialog_view_desc, null);
						builder.setView(dialogView);

						//get name and search for description of menu item
						String name = txtName.getText().toString();
						String desc = "";
						for (int i = 0; i < listMenu.size(); i++) {
							if (name.equals(listMenu.get(i).getName())) {
								desc = listMenu.get(i).getDesc();
							}
						}

						//set dialog textviews' name and description
						((TextView) dialogView.findViewById(R.id.food_name)).setText(name);
						((TextView) dialogView.findViewById(R.id.food_desc)).setText(desc);

						//set "Done" button click to close
						builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

						//show the dialog
						builder.create().show();
					}
				});

				//add food item to menu
				layout.addView(child);
			}
		}
	}

	//remake list of items ordered to send to order fragment
	public ArrayList<String> getOrder() {
		//reset list
		listOrders.clear();
		//create currency formatter
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		for (int i = 0; i < listMenu.size(); i++) {
			//only add if user ordered more than 0 items and the item isn't a section header
			if (listMenu.get(i).getQuantity() > 0 && !listMenu.get(i).isSection()) {
				//format item name & price (e.g. "Item Name$3.49"
				String name = listMenu.get(i).getName();
				String price = formatter.format(listMenu.get(i).getPrice());
				String item = name + price;
				//add item to order array for as many as user ordered
				for (int j = 0; j < listMenu.get(i).getQuantity(); j++) {
					listOrders.add(item);
				}
			}
		}

		return listOrders;
	}
}
