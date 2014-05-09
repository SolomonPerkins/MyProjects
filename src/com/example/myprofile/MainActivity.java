package com.example.myprofile;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//Include SQLite funcationality
import com.example.sqlite.Project;
import com.example.sqlite.ProjectsDao;

public class MainActivity extends ActionBarActivity {
	
	private ProjectsDao projectsDao;
	
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

		projectsDao = new ProjectsDao(this);
		
		
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
		projectsDao.open();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		
		dateFormat.format(d);
		projectsDao.createProject("Trial", "A basic description", "car.png", "", dateFormat);
		
		List<Project> projects = projectsDao.getAllProject("date", "DESC");
		
		
		
		ProjectItem[] projectItems = new ProjectItem[20];
		Calendar calendar = Calendar.getInstance();
		
		String today = "/Apr 5,2014";
		
		projectItems[0] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[1] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[2] = new ProjectItem(1, "Body Mass Index", today, "Some extra stuff", R.drawable.java);
		projectItems[3] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[4] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[5] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[6] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[7] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[8] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[9] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[10] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[11] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[12] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[13] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[14] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[15] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[16] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[17] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[18] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		projectItems[19] = new ProjectItem(1, "My Project", today, "Some extra stuff", R.drawable.programming_language);
		
		//Get ListView object
		projectsListView = (ListView) findViewById(R.id.projects_list);
		
		
		//Create the arrayadapter
//		ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.fragment_each_project, projectItems);
		ArrayAdapter<Project>  adapter = new ArrayAdapter<Project>(this, R.layout.fragment_each_project, projects);
		
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
