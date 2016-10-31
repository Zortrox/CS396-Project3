package com.foxslash.cs396_project3;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.*;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private ArrayList<String> listOrders = new ArrayList<>();
	private Fragment fragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//setup initial fragment
		fragment = new FragMenu();
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("orders", listOrders);
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.add(R.id.content_main, fragment).commit();

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		FragmentManager fragmentManager = getFragmentManager();
		if (fragment instanceof FragOrder) {
			listOrders = ((FragOrder) fragment).getOrder();
		} else if (fragment instanceof FragMenu) {
			listOrders = ((FragMenu) fragment).getOrder();
		}

		if (id == R.id.nav_orders) {
			fragment = new FragOrder();
			Bundle bundle = new Bundle();
			bundle.putStringArrayList("orders", listOrders);
			fragment.setArguments(bundle);
		} else if (id == R.id.nav_menu) {
			fragment = new FragMenu();
			Bundle bundle = new Bundle();
			bundle.putStringArrayList("orders", listOrders);
			fragment.setArguments(bundle);
		} else {
			fragment = new Fragment();
		}
		fragmentManager.beginTransaction()
				.replace(R.id.content_main, fragment)
				.commit();

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

}

