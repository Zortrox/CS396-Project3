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
import android.widget.ListView;
import java.util.ArrayList;

public class FragOrders extends Fragment {

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

		OrderAdapter adapter = new OrderAdapter(listItems, getActivity());
		ListView listView = (ListView) view.findViewById(R.id.item_list);
		listView.setAdapter(adapter);
	}

	public ArrayList<String> getOrder() {
		return listOrders;
	}

	private ArrayList<OrderItem> parseItems() {
		ArrayList<OrderItem> items = new ArrayList<>();

		for (int i = 0; i < listOrders.size(); i++) {
			boolean itemFound = false;
			for (int k = 0; k < items.size(); k++) {
				OrderItem tempItem = items.get(k);
				if (tempItem.getName() == listOrders.get(i)) {
					itemFound = true;
					tempItem.setQuantity(tempItem.getQuantity() + 1);
				}
			}
			if (!itemFound) {
				String strData = listOrders.get(i);

				String name = strData.substring(0, strData.indexOf('$'));

				//GET PRICE

				items.add(new OrderItem(strData))
			}
		}

		return items;
	}
}
