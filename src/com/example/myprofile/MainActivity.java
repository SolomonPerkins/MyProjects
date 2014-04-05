package com.example.myprofile;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	ListView projectsListView;
	ListView slideShowListView;
	Intent intent;
	
	public final static String INTENT_Message = "com.example.myproject.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Loads the data
		getProjectList();
		
		//Show slideshow list
//		getSlideShowList();
		/*FragmentManager fragManager = getFragmentManager();
		FragmentTransaction ft = fragManager.beginTransaction();
		DetailsView_Fragment df = new DetailsView_Fragment();
		ft.show(df);
		ft.commit();*/
		
//		hideDetailsView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		String message ="Click link";
		
		switch (id) {
		case R.id.action_about:
			intent = new Intent(this, AboutActivity.class);
			intent.putExtra(INTENT_Message, message);
			startActivity(intent);
			
			break;
		default:
			
			break;
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public void getProjectList(){
		
		ProjectItem[] projectItems = new ProjectItem[20];
		
		projectItems[0] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[1] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[2] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[3] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[4] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[5] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[6] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[7] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[8] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[9] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[10] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[11] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[12] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[13] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[14] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[15] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[16] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[17] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[18] = new ProjectItem(1, "My Project", "Some extra stuff");
		projectItems[19] = new ProjectItem(1, "My Project", "Some extra stuff");
		
		//Get ListView object
		projectsListView = (ListView) findViewById(R.id.projects_list);
		
		//Create the arrayadapter
		ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.fragment_each_project, projectItems);
		
		//Assign adapter to list
		projectsListView.setAdapter(adapter);	

		projectsListView.setOnItemClickListener(new onProjectItemClickListViewItem());
		
	}
	
	public void getSlideShowList(){
		SlideShowItem[] slideshowItems = new SlideShowItem[3];
		
		slideshowItems[0] = new SlideShowItem(1, "First Image");
		slideshowItems[1] = new SlideShowItem(2, "Second Image");
		slideshowItems[2] = new SlideShowItem(3, "Third Image");
	
		
		//Get listview object
		slideShowListView = (ListView)findViewById(R.id.main_slideshow_list);
		
		//Create the array adapter
		SlideShowArrayAdapterItem adapter = new SlideShowArrayAdapterItem(this, R.layout.fragment_slideshow_item, slideshowItems);
		
		//assign adapter to list
		slideShowListView.setAdapter(adapter);
		
	}

	public void hideDetailsView(){
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		//get the fragment to be hiden
		DetailsView_Fragment detailFragment = (DetailsView_Fragment)getFragmentManager().findFragmentById(R.id.details_fragment);
		View frgView = detailFragment.getView();
	
		//hide the fragment
		transaction.hide(detailFragment);
		
//		View detailFragment = (View) findViewById(R.id.details_fragment);
	
		transaction.commit();
		
	}
	
	public void showDetailsView(){
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		//get the fragment to be hiden
		DetailsView_Fragment detailFragment = (DetailsView_Fragment)getFragmentManager().findFragmentById(R.id.details_fragment);
		View frgView = detailFragment.getView();
	
		//hide the fragment
		transaction.show(detailFragment);
		
//		View detailFragment = (View) findViewById(R.id.details_fragment);
	
		transaction.commit();
		
	}
	
	
}
