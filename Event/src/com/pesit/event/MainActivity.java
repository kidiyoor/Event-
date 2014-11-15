package com.pesit.event;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
@SuppressLint("NewApi")
public class MainActivity extends Activity implements SearchView.OnQueryTextListener {
	
	//////////////////////////////
	////////////Kidiyoor
	//////////////////////////////
	public static int choice=-1;
	public ImageButton feed;
	public static String T;
	public static final Integer[] images1 = { R.drawable.fconf,R.drawable.fmeet,R.drawable.fente,R.drawable.ffund,R.drawable.fconvn,R.drawable.fperf,R.drawable.freun,R.drawable.fsale,R.drawable.fsemi,R.drawable.fsoci };

	public static final Integer[] images2 = { R.drawable.fspor,R.drawable.ftrad,R.drawable.ftrav,R.drawable.freli,R.drawable.ffair,R.drawable.ffood,R.drawable.fmusi,R.drawable.frecr};
	
		 ListView listView1;
		 ListView listView2;
	  	 private DrawerLayout mDrawerLayout;
		 private CharSequence mDrawerTitle;
		 private ListView mDrawerList;
		 private ActionBarDrawerToggle mDrawerToggle;

		 private List<SideItem> rowItems;
		 private SideAdapter adapter;
		 private CharSequence mTitle;
		 /** The view to show the ad. */
		  private AdView adView;

		  /* Your ad unit id. Replace with your actual ad unit id. */
		  //private static final String AD_UNIT_ID ="ca-app-pub-7676423146760649/4947285613";
		//  private static final String AD_UNIT_ID = "pub-7676423146760649";
		  private static final String AD_UNIT_ID = "ca-app-pub-7676423146760649/5130475210";
		  private SearchView mSearchView;
		    private TextView mStatusView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setTitle("");   
		setContentView(R.layout.activity_main);
		
		 final String[] actions = new String[] {
				/* "Bangalore",
				 "Ahmedabad","Australia","Agra",
				 "brazil","Bhubaneswar",
				 "Canada","chennai","Coimbatore","Chandigarh",
				 "Delhi",
				 "Gurgaon","Gulbarga",
				 "Hyderabad",
				 "india","indore",
				 "Jammu",
				 "Thane",
				 "Bhopal",
				 "Patna",
				 "Jaipur",
				 "Kanpur",
			     "london","Lucknow",
			     "Mangalore","Mumbai","Mysore",
			     "Nagpur",
			     "Pune","Puducherry",
			     "spain","south africa",
			     "Thiruvananthapuram"
			      */
				 "Bangalore","Argentina", "Australia", "Brasil" ,"Canada","Chile",  "Colombia",  "Deutschland",  "Espana",  "France",  "Hong Kong",  "Ireland",  "Italia Mexico",  "Nederland",  "New Zealand",  "Peru",  "Portugal",  "Singapore",  "United Kingdom"
			    };
			 
		 final String[] actions1 = new String[] {
				 
					 "--Location --",
					 "Ahmedabad","Australia","Agra",
					 "brazil","Bhubaneswar",
					 "Canada","chennai","Coimbatore","Chandigarh",
					 "Delhi",
					 "Gurgaon","Gulbarga",
					 "Hyderabad",
					 "india","indore",
					 "Jammu",
					 "Thane",
					 "Bhopal",
					 "Patna",
					 "Jaipur",
					 "Kanpur",
				     "london","Lucknow",
				     "Mangalore","Mumbai","Mysore",
				     "Nagpur",
				     "Pune","Puducherry",
				     "spain","south africa",
				     "Thiruvananthapuram",
					 "Bangalore","Argentina", "Australia", "Brasil" ,"Canada","Chile",  "Colombia",  "Deutschland",  "Espana",  "France",  "Hong Kong",  "Ireland",  "Italia Mexico",  "Nederland",  "New Zealand",  "Peru",  "Portugal",  "Singapore",  "United Kingdom"
				    };
			
		/////////////side list
		 String[] menutitles = getResources().getStringArray(R.array.titles);
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	         mDrawerList = (ListView) findViewById(R.id.slider_list);
	        mTitle = mDrawerTitle = getTitle();
	        mDrawerList.setBackgroundResource(R.drawable.grey);
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                mDrawerLayout,         /* DrawerLayout object */
	              //  R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
	                //R.string.drawer_open,  /* "open drawer" description */
	                //R.string.drawer_close  /* "close drawer" description */
	                R.drawable.drawer,  /* "close drawer" description */
	                R.string.action_settings, /* "close drawer" description */
	                R.string.action_settings  /* "close drawer" description */
	        		) {

	            /** Called when a drawer has settled in a completely closed state. */
	            @SuppressLint("NewApi")
				public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            @SuppressLint("NewApi")
				public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	            }
	        };

	        // Set the drawer toggle as the DrawerListener
	        mDrawerLayout.setDrawerListener(mDrawerToggle);

	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	        if (Build.VERSION.SDK_INT >= 18) {
	            getActionBar().setHomeAsUpIndicator(
	                getResources().getDrawable(R.drawable.drawer));
	        }
	        
	        
	        /////////// drop down
	        
	       //  Create an array adapter to populate dropdownlist 
	        ArrayAdapter<String> adapterLoc = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, actions1);
	 
	        // Enabling dropdown list navigation for the action bar 
	        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	        
	 
	        /** Defining Navigation listener  */
	        ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
	 
	            @Override
	            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
	            	if(itemPosition!=0)
	            	{
	            	if(!actions1[itemPosition].equalsIgnoreCase("any"))
	            		SplashScreen.key=actions1[itemPosition];
	            	else
		            	SplashScreen.key="";
	                
	            	}
	            	 Toast.makeText(getBaseContext(), "Location selected : " + SplashScreen.key  , Toast.LENGTH_SHORT).show();
	                return false;
	            }
	        };
	 
	        // Setting dropdown items and item navigation listener for the actionbar 
	        getActionBar().setListNavigationCallbacks(adapterLoc, navigationListener);
	        
	        
	        ////// end drop down
	        
	        rowItems = new ArrayList<SideItem>();
	        for (int i = 0; i < actions.length; i++) {
	        	   SideItem items = new SideItem(actions[i]);
	        	   rowItems.add(items);
	        	  }

	        adapter = new SideAdapter(getApplicationContext(), rowItems);

	       
	        mDrawerList.setAdapter(adapter);
	        
	        //////
	        
		    List<RowItem> rowItems1=null;
		    List<RowItem> rowItems2 = null;
		    RowItem item1;
		    rowItems1 = new ArrayList<RowItem>();
		    for (int i = 0; i < images1.length; i++) {
	            item1 = new RowItem(images1[i]);
	            rowItems1.add(item1);
	        }
		    rowItems2 = new ArrayList<RowItem>();
			   
		    for (int i = 0; i < images2.length; i++) {
	            item1 = new RowItem(images2[i]);
	            rowItems2.add(item1);
	        }
		    
		    listView1 = (ListView) findViewById(R.id.listView1);
		    listView2 = (ListView) findViewById(R.id.listView2);
	        HomeAdapter adapter1 = new HomeAdapter(this,
	                R.layout.list_item, rowItems1);
	        listView1.setAdapter(adapter1);
	        HomeAdapter adapter2 = new HomeAdapter(this,
	                R.layout.list_item, rowItems2);
	        listView2.setAdapter(adapter2);
	     
	        //   listView1.setOnItemClickListener(this);
		feed=(ImageButton)findViewById(R.id.feed);
	
				feed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
choice=1;
if(SplashScreen.key==null)
SplashScreen.key="bangalore";
				Intent i =new Intent(MainActivity.this,SplashScreen.class);
				startActivity(i);
				finish();
		
			}
		});
				
			
		
				listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				    @Override
				    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

if(SplashScreen.key==null)
SplashScreen.key="bangalore";
				    	choice=10+pos;
						
						Intent i =new Intent(MainActivity.this,SplashScreen.class);
						startActivity(i);  
						finish();									
				    }

					
					
				});
		
		
				listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				    @Override
				    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

if(SplashScreen.key==null)
SplashScreen.key="bangalore";
				    	choice=20+pos;
						
						Intent i =new Intent(MainActivity.this,SplashScreen.class);
						startActivity(i);  
						finish();
				    }

					
					
				});
		
	
				mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				    @Override
				    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
				    	
				    	SplashScreen.key=actions[pos];
		            	T="Location selected : " + actions[pos];
				    	Toast.makeText(getBaseContext(), T  , Toast.LENGTH_LONG).show();
				    	mDrawerLayout.closeDrawer(Gravity.LEFT);
						    
				    }

					
					
				});
				
			//	mDrawerLayout.openDrawer(Gravity.LEFT);
	
				
				
				
				
				// Create an ad.
			    adView = new AdView(this);
			    adView.setAdSize(AdSize.BANNER); 
			    adView.setAdUnitId(AD_UNIT_ID);
			    AdRequest adRequest = new AdRequest.Builder()
			   //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Emulator
			   // .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4") // My Galaxy Nexus test phone
			    		.addTestDevice("D5F4A91730268449FEBBB51FFBF9EA39")
			    .build();

			    // Add the AdView to the view hierarchy. The view will have no size
			    // until the ad is loaded.
			    LinearLayout layout = (LinearLayout) findViewById(R.id.homeAds);
			    layout.addView(adView);

			    // Create an ad request. Check logcat output for the hashed device ID to
			    // get test ads on a physical device.
			    /*
			    AdRequest adRequest = new AdRequest.Builder()
			        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			        .addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
			        .build();
			*/
			    // Start loading the ad in the background.
			    adView.loadAd(adRequest);
		
			    Toast.makeText(getBaseContext(), "Choose Location ...", Toast.LENGTH_SHORT).show();
					
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		 MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.searchview_in_menu, menu);
	        MenuItem searchItem = menu.findItem(R.id.action_search);
	        mSearchView = (SearchView) searchItem.getActionView();
	        setupSearchView(searchItem);

		return true;
	}
	 @SuppressLint("NewApi")
	private void setupSearchView(MenuItem searchItem) {

	        if (isAlwaysExpanded()) {
	            mSearchView.setIconifiedByDefault(false);
	        } else {
	            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
	                    | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	        }

	        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        if (searchManager != null) {
	            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();

	            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
	            for (SearchableInfo inf : searchables) {
	                if (inf.getSuggestAuthority() != null
	                        && inf.getSuggestAuthority().startsWith("applications")) {
	                    info = inf;
	                }
	            }
	            mSearchView.setSearchableInfo(info);
	        }

	        mSearchView.setOnQueryTextListener((OnQueryTextListener) this);
	    }
	 public boolean onQueryTextChange(String newText) {
	        //mStatusView.setText("Query = " + newText);
		 SplashScreen.key=newText;
     	   
	        return false;
	    }

	    public boolean onQueryTextSubmit(String query) {
	        //mStatusView.setText("Query = " + query + " : submitted");
	    	SplashScreen.key=query;
        	Toast.makeText(getBaseContext(), "Location selected : " + SplashScreen.key  , Toast.LENGTH_SHORT).show();
	        return false;
	    }

	    public boolean onClose() {
	        //mStatusView.setText("Closed!");
	    	Toast.makeText(getBaseContext(), "Location selected : " + SplashScreen.key  , Toast.LENGTH_SHORT).show();
		      
	    	return false;
	    }

	    protected boolean isAlwaysExpanded() {
	        return false;
	    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	      // The action bar home/up action should open or close the drawer.
	      // ActionBarDrawerToggle will take care of this.
	      if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	      }
	 
	      return false;
	}
	 
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	      super.onConfigurationChanged(newConfig);
	      // Pass any configuration change to the drawer toggles
	      mDrawerToggle.onConfigurationChanged(newConfig);
	}
	}
