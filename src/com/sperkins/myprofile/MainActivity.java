package com.sperkins.myprofile;


import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
//Include SQLite funcationality
import android.widget.Toast;
//include basic date utils functions

import com.example.myprofile.R;
import com.sperkins.myprofile.models.ProjectListView;
import com.sperkins.myprofile.services.localDbService;

public class MainActivity extends ActionBarActivity {
	
	private localDbService localdb;
	
	ListView projectsListView;
	ListView slideShowListView;
	Intent intent;
	
	//MyFragments
	DetailsView_Fragment myDetailsFragment;
	
	boolean mIsAnimating = false;
	boolean mSlideUp = false;
	
	public final static String INTENT_Message = "com.example.myproject.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//The wrapper for all local storage functions
		//Also sync the database provided that there is new incoming data
		localdb = new localDbService(this);
				
		setUpDetailsView();
		
		//Loads the data
		getProjectList();
		
		//Show slideshow list
//		getSlideShowList();
		
		/*FragmentManager fragManager = getFragmentManager();
		FragmentTransaction ft = fragManager.beginTransaction();
		DetailsView_Fragment df = new DetailsView_Fragment();
		ft.show(df);
		ft.commit();*/
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * When a user selects an option menu it loads activity if about/contacts was selected
	 * And clicking the backbutton should return it to the previous activity
	 */
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

	
	public void getProjectList(){
		
		//Open the database
//		projectsDao.open();
//		Date date = new Date();
		
//		projectsDao.insertDa();
//		projectsDao.insertDa();
//		projectsDao.insertDa();
//		projectsDao.insertDa();
//		projectsDao.insertDa();
		
//		projectsDao.createProject("Trial",  "2014-04-04", "Welcome to the end of the world");		
		List<ProjectListView> projects = localdb.getProjects();
		Toast.makeText(getApplicationContext(), "Total projects " + projects.size(), Toast.LENGTH_LONG).show();
		
		//Get ListView object
		projectsListView = (ListView) findViewById(R.id.projects_list);
		
		//Create the arrayadapter
//		ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.fragment_each_project, projectItems);
		ArrayAdapterItem  adapter = new ArrayAdapterItem(this, R.layout.fragment_each_project, projects);
		
		//Assign adapter to list
		projectsListView.setAdapter(adapter);	
		
		//Set up the event listener
		projectsListView.setOnItemClickListener(new onProjectItemClickListViewItem(myDetailsFragment));
		
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

	public void setUpDetailsView(){
		//Get the fragment and hide it
		myDetailsFragment= (DetailsView_Fragment)getFragmentManager().findFragmentById(R.id.details_fragment);

		myDetailsFragment.hideDetailsView();
	}
		
	
}
