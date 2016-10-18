package com.foxslash.cs396_project3;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

	Context mainContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//add list data
		final PersonAdapter adapter = new PersonAdapter(new ArrayList<String>(), this);
		ListView lView = (ListView)findViewById(R.id.person_list);
		lView.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				adapter.addPerson("Jeffrey");
			}
		});

	}

	private class PersonAdapter extends BaseAdapter implements ListAdapter {
		private ArrayList<String> list = new ArrayList<String>();
		private Context context;

		public PersonAdapter(ArrayList<String> list, Context context) {
			this.list = list;
			this.context = context;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int pos) {
			return list.get(pos);
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
				nowView = inflater.inflate(R.layout.person_wrapper, null);
			}
			final View view = nowView;

			//layout
			RelativeLayout topLevel = (RelativeLayout)view.findViewById(R.id.person_box);
			topLevel.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					LinearLayout items = (LinearLayout)view.findViewById(R.id.person_item_list);
					if (items.getVisibility() == View.VISIBLE) {
						items.setVisibility(View.GONE);
					} else {
						items.setVisibility(View.VISIBLE);
					}
				}
			});

			//Change item text
			TextView listItemText = (TextView)view.findViewById(R.id.person_name);
			listItemText.setText(list.get(position));

			//Add item button
			ImageButton addBtn = (ImageButton)view.findViewById(R.id.person_add_item);
			addBtn.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					//Toast.makeText(MainActivity.this, "Adding item",
							//Toast.LENGTH_LONG).show();

					LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					LinearLayout layout = (LinearLayout)view.findViewById(R.id.person_item_list);
					View child = inflater.inflate(R.layout.item_wrapper, null);
					TextView text = (TextView)child.findViewById(R.id.item_name);
					text.setText("Burger");
					layout.addView(child);

					layout.setVisibility(View.VISIBLE);

					view.invalidate();
					notifyDataSetChanged();
				}
			});

			return view;
		}

		public void addPerson(String name) {
			list.add(name);
			notifyDataSetChanged();
		}
	}

}
