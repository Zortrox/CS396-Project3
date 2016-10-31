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

public class CostsAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private ArrayList<String> listCusts = null;

	public CostsAdapter(ArrayList<String> list, Context context) {
		listCusts = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listCusts.size();
	}

	@Override
	public Object getItem(int pos) {
		return listCusts.get(pos);
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
			nowView = inflater.inflate(R.layout.cost_wrapper, null);
		}
		final View view = nowView;

		//Change item data
		TextView custCharge = (TextView)view.findViewById(R.id.text_charge);
		String strCharge = listCusts.get(position).substring(listCusts.get(position).indexOf('$'));
		custCharge.setText(strCharge);

		TextView custNum = (TextView)view.findViewById(R.id.text_num);
		String strNum = listCusts.get(position).substring(0, listCusts.get(position).indexOf('$'));
		custNum.setText(strNum);

		return view;
	}

	public void newList(ArrayList<String> list) {
		listCusts = list;
		notifyDataSetChanged();
	}

	public void clearList() {
		listCusts.clear();
		notifyDataSetChanged();
	}
}
