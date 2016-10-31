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

public class FragOrder extends Fragment {

	ArrayList<String> listOrders = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		listOrders = bundle.getStringArrayList("orders");

		return inflater.inflate(R.layout.frag_orders, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ArrayList<OrderItem> listItems = parseItems();

		final OrderAdapter adapter = new OrderAdapter(listItems, getActivity());
		ListView listView = (ListView) view.findViewById(R.id.item_list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View dialogView = inflater.inflate(R.layout.dialog_edit_order, null);

				builder.setView(dialogView);

				TextView listItemQuantity = (TextView) view.findViewById(R.id.item_quantity);
				int qty = Integer.valueOf(listItemQuantity.getText().toString());
				NumberPicker numPicker = (NumberPicker) dialogView.findViewById(R.id.number_picker);
				numPicker.setMinValue(0);
				numPicker.setMaxValue(99);
				numPicker.setWrapSelectorWheel(false);
				numPicker.setValue(qty);

				// Add action buttons
				final View innerView = view;
				final int removePosition = position;
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						NumberPicker numPicker = (NumberPicker) dialogView.findViewById(R.id.number_picker);
						int qty = numPicker.getValue();

						((TextView) innerView.findViewById(R.id.item_quantity)).setText(String.valueOf(qty));

						String price = ((TextView) innerView.findViewById(R.id.text_charge)).getText().toString();
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String total = formatter.format(qty * Double.valueOf(price.substring(1)));
						((TextView) innerView.findViewById(R.id.item_total)).setText(total);
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
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

		final View innerView = view;
		FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_pay);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View dialogView = inflater.inflate(R.layout.dialog_tip_amount, null);
				builder.setView(dialogView);

				double sub = 0.0;
				ListView listView = (ListView) innerView.findViewById(R.id.item_list);
				for (int i = 0; i < listView.getChildCount(); i++) {
					String price = ((TextView) listView.getChildAt(i).findViewById(R.id.item_total)).getText().toString();
					price = price.substring(1);
					sub += Double.valueOf(price);
				}
				final double subtotal = sub;

				SeekBar seek = (SeekBar) dialogView.findViewById(R.id.seek_tip);
				seek.setMax(50);
				seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						((TextView) dialogView.findViewById(R.id.text_tip_percent)).setText(String.valueOf(progress));
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}
				});

				TextView txtPercent = (TextView) dialogView.findViewById(R.id.text_tip_percent);
				txtPercent.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {

					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						int intTip = Integer.valueOf(s.toString());

						if (intTip >= 0) {
							NumberFormat formatter = NumberFormat.getCurrencyInstance();
							String tip = formatter.format(subtotal * ((double) intTip / 100.0));
							((TextView) dialogView.findViewById(R.id.text_tip_dollars)).setText(tip);
						}
					}

					@Override
					public void afterTextChanged(Editable s) {

					}
				});

				TextView txtTip = (TextView) dialogView.findViewById(R.id.text_tip_dollars);
				txtTip.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {

					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						String tip = ((TextView) dialogView.findViewById(R.id.text_tip_dollars)).getText().toString();
						double dblTip = Double.valueOf(tip.substring(1));

						double dblTotal = (subtotal + dblTip) * 1.06;

						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String total = formatter.format(dblTotal);
						((TextView) dialogView.findViewById(R.id.text_total)).setText(total);
					}

					@Override
					public void afterTextChanged(Editable s) {

					}
				});

				// Add action buttons
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						AlertDialog.Builder splitBuilder = new AlertDialog.Builder(getActivity());
						LayoutInflater inflater = LayoutInflater.from(getActivity());
						final View splitDialogView = inflater.inflate(R.layout.dialog_split_bill, null, false);
						splitBuilder.setView(splitDialogView);

						String total = ((TextView) dialogView.findViewById(R.id.text_total)).getText().toString();
						final double dblTotal = Double.valueOf(total.substring(1));

						final ListView list = (ListView) splitDialogView.findViewById(R.id.list_costs);
						final CostsAdapter costsAdapter = new CostsAdapter(new ArrayList<String>(), getActivity());
						list.setAdapter(costsAdapter);

						NumberPicker numPicker = (NumberPicker) splitDialogView.findViewById(R.id.number_picker);
						numPicker.setMinValue(1);
						numPicker.setMaxValue(99);
						numPicker.setWrapSelectorWheel(false);
						numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
							@Override
							public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
								ArrayList<String> listCharges = new ArrayList<>();

								//make sure no pennies are missing
								long lngTotal = Math.round(dblTotal * 100);
								long numPennies = lngTotal % newVal;

								NumberFormat formatter = NumberFormat.getCurrencyInstance();
								for (int i = 0; i < newVal; i++) {
									long customerCharge = lngTotal / newVal;
									if (numPennies > 0) {
										customerCharge += 1;
										numPennies--;
									}

									String customer = String.valueOf(i + 1) + formatter.format(1.0d * customerCharge / 100.0);
									listCharges.add(customer);
								}

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

						dialog.cancel();

						//calculate initial costs
						ArrayList<String> listCharges = new ArrayList<>();
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						long lngTotal = Math.round(dblTotal * 100);
						String customer = String.valueOf(1) + formatter.format(1.0d * lngTotal / 100.0);
						listCharges.add(customer);
						costsAdapter.newList(listCharges);

						//show dialog
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

				builder.create().show();
			}
		});
	}

	public ArrayList<String> getOrder() {
		listOrders.clear();

		ListView listView = (ListView) getView().findViewById(R.id.item_list);
		for (int i = 0; i < listView.getChildCount(); i++) {
			TextView listItemQuantity = (TextView) listView.getChildAt(i).findViewById(R.id.item_quantity);
			int qty = Integer.valueOf(listItemQuantity.getText().toString());
			if (qty > 0) {
				String name = ((TextView) listView.getChildAt(i).findViewById(R.id.label_customer)).getText().toString();
				String price = ((TextView) listView.getChildAt(i).findViewById(R.id.text_charge)).getText().toString();
				String item = name + price;
				for (int j = 0; j < qty; j++) {
					listOrders.add(item);
				}
			}
		}

		return listOrders;
	}

	private ArrayList<OrderItem> parseItems() {
		ArrayList<OrderItem> items = new ArrayList<>();

		for (int i = 0; i < listOrders.size(); i++) {
			boolean itemFound = false;
			for (int k = 0; k < items.size(); k++) {
				OrderItem tempItem = items.get(k);
				String name = listOrders.get(i).substring(0, listOrders.get(i).indexOf('$'));
				if (tempItem.getName().equals(name)) {
					itemFound = true;
					tempItem.setQuantity(tempItem.getQuantity() + 1);
				}
			}
			if (!itemFound) {
				String strData = listOrders.get(i);

				String name = strData.substring(0, strData.indexOf('$'));
				String price = strData.substring(strData.indexOf('$'));

				Double p = Double.valueOf(price.substring(1));

				items.add(new OrderItem(name, p));
				items.get(items.size() - 1).setQuantity(1);
			}
		}

		return items;
	}
}
