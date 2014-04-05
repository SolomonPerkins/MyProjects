package com.example.myprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AboutActivity extends ActionBarActivity {

	ListView myDetailsListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		
		getMyInfo();
		
		//Get the Intent
//		Intent intent = getIntent();
		
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_about,
					container, false);
			return rootView;
		}
	}
	
	public void getMyInfo(){
		MyDetailsItem[] myDetailsItems = new MyDetailsItem[5];
		
		myDetailsItems[0] =  new MyDetailsItem(1, "Education /Secondary", "Clarendon College");
		myDetailsItems[1] =  new MyDetailsItem(2, "Education /Teritary", "University of Technology");
		myDetailsItems[2] =  new MyDetailsItem(3, "Education /Teritary /Major", "Computer Science");
		myDetailsItems[3] =  new MyDetailsItem(4, "Employment /History", "Victoria Mutural Bank");
		myDetailsItems[4] =  new MyDetailsItem(5, "Employment /Current", "Medullan");
		

		//Get listview
		myDetailsListView = (ListView) findViewById(R.id.my_details_list_fragment);
		
		//Create the array adapter
		MyDetailsArrayAdapterItem myadapter = new MyDetailsArrayAdapterItem(this, R.layout.my_details_list, myDetailsItems);
		
		//set adapter to list
		myDetailsListView.setAdapter(myadapter);
	}

}
