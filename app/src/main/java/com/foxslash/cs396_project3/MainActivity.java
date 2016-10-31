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

//main activity of the app
//contains both fragments, app bar, and menu
public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	//user food item orders
	private ArrayList<String> listOrders = new ArrayList<>();
	//current fragment (order or menu screen)
	private Fragment fragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//set main view
		setContentView(R.layout.activity_main);
		//get toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//setup initial fragment (menu)
		fragment = new FragMenu();

		//create bundle for passing initial array of empty order
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("orders", listOrders);
		//add bundle to fragment argument
		fragment.setArguments(bundle);

		//add fragment to activity and start
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.add(R.id.content_main, fragment).commit();

		//get navigation drawer & set toggle action (to open)
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		//set navigation drawer item select listener
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		//close navigation drawer if open, otherwise let the backend handle the back button
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	//handle navigation view item selection (click)
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		//selected item
		int id = item.getItemId();

		//get current fragment to get the current order
		FragmentManager fragmentManager = getFragmentManager();
		if (fragment instanceof FragOrder) {
			listOrders = ((FragOrder) fragment).getOrder();
		} else if (fragment instanceof FragMenu) {
			listOrders = ((FragMenu) fragment).getOrder();
		}

		if (id == R.id.nav_orders) {
			//add argument for user orders when switching to order screen
			fragment = new FragOrder();
			Bundle bundle = new Bundle();
			bundle.putStringArrayList("orders", listOrders);
			fragment.setArguments(bundle);
		} else if (id == R.id.nav_menu) {
			//add argument for user orders when switching to menu screen
			fragment = new FragMenu();
			Bundle bundle = new Bundle();
			bundle.putStringArrayList("orders", listOrders);
			fragment.setArguments(bundle);
		} else {
			//create a blank fragment (should never happen)
			fragment = new Fragment();
		}

		//switch fragment to selected nav item
		fragmentManager.beginTransaction()
				.replace(R.id.content_main, fragment)
				.commit();

		//close the navigation drawer
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

}

