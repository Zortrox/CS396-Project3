/**
 * Created by Zortrox on 10/26/2016.
 */

package com.foxslash.cs396_project3;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.NumberFormat;
import java.util.ArrayList;

//fragment to handle
public class FragOrder extends Fragment {

	//array of orders in the form "Item Name$3.25"
	ArrayList<String> listOrders = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//get order array from argument
		Bundle bundle = getArguments();
		listOrders = bundle.getStringArrayList("orders");

		return inflater.inflate(R.layout.frag_orders, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		//create array of order items
		ArrayList<OrderItem> listItems = parseItems();

		//add adapter to order lsitview for custom layout
		final OrderAdapter adapter = new OrderAdapter(listItems, getActivity());
		ListView listView = (ListView) view.findViewById(R.id.item_list);
		listView.setAdapter(adapter);

		//set item click listener for each listview item
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {
				//display a custom dialog to edit the current order item
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View dialogView = inflater.inflate(R.layout.dialog_edit_order, null);
				builder.setView(dialogView);

				//get current quantity of the item
				TextView listItemQuantity = (TextView) view.findViewById(R.id.item_quantity);
				int qty = Integer.valueOf(listItemQuantity.getText().toString());
				//set the number picker widget min & max values and current qty
				NumberPicker numPicker = (NumberPicker) dialogView.findViewById(R.id.number_picker);
				numPicker.setMinValue(0);
				numPicker.setMaxValue(99);
				numPicker.setWrapSelectorWheel(false);	//don't wrap the wheel from 0 to 99
				numPicker.setValue(qty);

				//add action buttons to dialog
				final View innerView = view;
				final int removePosition = position;

				//set new quantity for item
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						//get the quantity from the number picker
						NumberPicker numPicker = (NumberPicker) dialogView.findViewById(R.id.number_picker);
						int qty = numPicker.getValue();

						//set the quantity for the order item
						((TextView) innerView.findViewById(R.id.item_quantity)).setText(String.valueOf(qty));

						//set the total price (qty * price) for the order item
						String price = ((TextView) innerView.findViewById(R.id.text_charge)).getText().toString();
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String total = formatter.format(qty * Double.valueOf(price.substring(1)));
						((TextView) innerView.findViewById(R.id.item_total)).setText(total);
					}
				});
				//close dialog window
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				//remove item from listview
				builder.setNeutralButton("Delete",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int id)
							{
								adapter.removeItem(removePosition);
							}
						});;

				builder.create().show();
			}
		});

		//set the floating action button click listener
		final View innerView = view;
		FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_pay);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//display custom dialog for tip amount
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View dialogView = inflater.inflate(R.layout.dialog_tip_amount, null);
				builder.setView(dialogView);

				//find the subtotal by adding each orderitem total
				double sub = 0.0;
				ListView listView = (ListView) innerView.findViewById(R.id.item_list);
				for (int i = 0; i < listView.getChildCount(); i++) {
					//add total to current subtotal
					String price = ((TextView) listView.getChildAt(i).findViewById(R.id.item_total)).getText().toString();
					price = price.substring(1);
					sub += Double.valueOf(price);
				}
				//final variable to be used in click listeners
				final double subtotal = sub;

				//these three listeners cascade to that a change to one will only affect those after it
				//seekbar -> tip percent -> tip amount
				//so that you can still specify higher or more specific amounts

				//set the max tip amount from the seekbar (50%) & change listener
				SeekBar seek = (SeekBar) dialogView.findViewById(R.id.seek_tip);
				seek.setMax(50);
				seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						//set the percent textview to match seekbar value
						((TextView) dialogView.findViewById(R.id.text_tip_percent)).setText(String.valueOf(progress));
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}
				});

				//set tip percent textview change listener
				TextView txtPercent = (TextView) dialogView.findViewById(R.id.text_tip_percent);
				txtPercent.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {

					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						//set tip percent to 0 if input is blank
						String val = s.toString();
						if (val.equals("")) val = "0";

						//convert text to integer
						int intTip = Integer.valueOf(val);
						if (intTip >= 0) {
							//update tip dollar amount textview
							NumberFormat formatter = NumberFormat.getCurrencyInstance();
							String tip = formatter.format(subtotal * ((double) intTip / 100.0));
							((TextView) dialogView.findViewById(R.id.text_tip_dollars)).setText(tip);
						}
					}

					@Override
					public void afterTextChanged(Editable s) {

					}
				});

				//add text changed listener for tip dollar amount
				TextView txtTip = (TextView) dialogView.findViewById(R.id.text_tip_dollars);
				txtTip.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {

					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						//get tip amount & convert to double
						String tip = ((TextView) dialogView.findViewById(R.id.text_tip_dollars)).getText().toString();

						//make sure tip amount is formatted correctly
						if (tip.equals("$") || tip.equals("")) tip = "$0";
						if (tip.charAt(0) != '$') tip = "$" + tip;

						double dblTip = Double.valueOf(tip.substring(1));

						//get total + tax
						double dblTotal = subtotal * 1.06  + dblTip;

						//set total textview
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String total = formatter.format(dblTotal);
						((TextView) dialogView.findViewById(R.id.text_total)).setText(total);
					}

					@Override
					public void afterTextChanged(Editable s) {
						//make sure dollar sign is shown on tip amount
						if (s.length() > 0 && s.charAt(0) != '$') s.insert(0, "$");
					}
				});

				// Add action buttons
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						//create custom dialog to split total
						AlertDialog.Builder splitBuilder = new AlertDialog.Builder(getActivity());
						LayoutInflater inflater = LayoutInflater.from(getActivity());
						final View splitDialogView = inflater.inflate(R.layout.dialog_split_bill, null, false);
						splitBuilder.setView(splitDialogView);

						//get total from tip dialog
						String total = ((TextView) dialogView.findViewById(R.id.text_total)).getText().toString();
						final double dblTotal = Double.valueOf(total.substring(1));

						//get listview for what each customer owes & set custom adapter
						final ListView list = (ListView) splitDialogView.findViewById(R.id.list_costs);
						final CostsAdapter costsAdapter = new CostsAdapter(new ArrayList<String>(), getActivity());
						list.setAdapter(costsAdapter);

						//set numberpicker min & max values and don't wrap
						NumberPicker numPicker = (NumberPicker) splitDialogView.findViewById(R.id.number_picker);
						numPicker.setMinValue(1);
						numPicker.setMaxValue(99);
						numPicker.setWrapSelectorWheel(false);
						//set value changed listener
						numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
							@Override
							public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
								//create array for each customer num and their charge
								ArrayList<String> listCharges = new ArrayList<>();

								//make sure no pennies are missing by taking the modulus
								//of the total by the number of customers
								long lngTotal = Math.round(dblTotal * 100);
								long numPennies = lngTotal % newVal;

								//loop through number of customers
								NumberFormat formatter = NumberFormat.getCurrencyInstance();
								for (int i = 0; i < newVal; i++) {
									//add penny if needed
									long customerCharge = lngTotal / newVal;
									if (numPennies > 0) {
										customerCharge += 1;
										numPennies--;
									}

									//concatenate string with customer num and currency formatted charge
									String customer = String.valueOf(i + 1) + formatter.format(1.0d * customerCharge / 100.0);
									listCharges.add(customer);
								}

								//add new array to listview adapter
								costsAdapter.newList(listCharges);
							}
						});

						//set total text for split cost dialog
						((TextView) splitDialogView.findViewById(R.id.text_total)).setText(total);

						// Add action buttons
						splitBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

						//close current tip dialog
						dialog.cancel();

						//calculate initial cost for 1 customer
						ArrayList<String> listCharges = new ArrayList<>();
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String customer = String.valueOf(1) + total;
						listCharges.add(customer);
						costsAdapter.newList(listCharges);

						//show split bill dialog
						splitBuilder.create().show();
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

				//calculate initial tip
				seek.setProgress(15);

				//show tip dialog
				builder.create().show();
			}
		});
	}

	//get string list to pass into fragment argument
	public ArrayList<String> getOrder() {
		//reset order list
		listOrders.clear();

		//loop through order listview
		ListView listView = (ListView) getView().findViewById(R.id.item_list);
		for (int i = 0; i < listView.getChildCount(); i++) {
			//if quantity wasn't reduced to 0 (instead of removal for some reason)
			TextView listItemQuantity = (TextView) listView.getChildAt(i).findViewById(R.id.item_quantity);
			int qty = Integer.valueOf(listItemQuantity.getText().toString());
			if (qty > 0) {
				//concatenate name and price
				String name = ((TextView) listView.getChildAt(i).findViewById(R.id.item_name)).getText().toString();
				String price = ((TextView) listView.getChildAt(i).findViewById(R.id.item_price)).getText().toString();
				String item = name + price;
				//add to array for each quantity ordered
				for (int j = 0; j < qty; j++) {
					listOrders.add(item);
				}
			}
		}

		return listOrders;
	}

	//turn string array of orders into OrderItems with quantity attached
	private ArrayList<OrderItem> parseItems() {
		//create new OrderItem array
		ArrayList<OrderItem> items = new ArrayList<>();

		//loop through string array of orders
		for (int i = 0; i < listOrders.size(); i++) {
			//loop through array of OrderItems to increase quantity
			boolean itemFound = false;
			for (int k = 0; k < items.size(); k++) {
				//if order found (this name = item name) increase the quantity
				OrderItem tempItem = items.get(k);
				String name = listOrders.get(i).substring(0, listOrders.get(i).indexOf('$'));
				if (tempItem.getName().equals(name)) {
					itemFound = true;
					tempItem.setQuantity(tempItem.getQuantity() + 1);
				}
			}
			//if item wasn't found in array already
			if (!itemFound) {
				//split string into name and price
				String strData = listOrders.get(i);
				String name = strData.substring(0, strData.indexOf('$'));
				String price = strData.substring(strData.indexOf('$'));

				//create double from price (remove dollar sign)
				Double p = Double.valueOf(price.substring(1));

				//add new OrderItem with name & price to array
				items.add(new OrderItem(name, p));
				items.get(items.size() - 1).setQuantity(1);
			}
		}

		return items;
	}
}
