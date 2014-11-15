package com.pesit.event;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi")
public class Feed extends FragmentActivity implements SearchView.OnQueryTextListener {
	GoogleMap map ;
	ListView eventList;
	ImageButton nav;
int mapProper=0;
	public static String result=null;
	ArrayList event_data;
	ArrayList event_data_backup;
	public static Event ItemLocStatic;
	EventAdapter adapter;
	private ProgressDialog progressDialog;
	ImageButton search;
	AutoCompleteTextView key;
	ImageButton profile;
	
	  private SearchView mSearchView;
	    private TextView mStatusView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setTitle("Enter key word -->");   
		
		setContentView(R.layout.activity_feed);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		nav=(ImageButton)findViewById(R.id.nav);
		map = ((SupportMapFragment) getSupportFragmentManager()
	              .findFragmentById(R.id.map)).getMap();
		
		nav.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String[] l = null;
				if(!ItemLocStatic.loc.equalsIgnoreCase("no"))
				{
					l=ItemLocStatic.loc.split("/");
				}
				Uri uri = Uri.parse("http://maps.google.com/maps?saddr=&daddr="+Double.parseDouble(l[0])+","+Double.parseDouble(l[1]));
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		profile=(ImageButton)findViewById(R.id.profile);
		profile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i= new Intent(Feed.this,Profile.class);
			startActivity(i);
			}
		});

	/*	progressDialog = ProgressDialog.show(Feed.this, "", "Loading...");

		new Thread() {

		public void run() {

		try{

		sleep(10000);

		} catch (Exception e) {

		Log.e("tag", e.getMessage());

		}

		// dismiss the progress dialog

		progressDialog.dismiss();

		}

		}.start();	*/
		try {
	        MapsInitializer.initialize(getApplicationContext());
	    } catch (GooglePlayServicesNotAvailableException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    mapProper=-1;
	    }
		event_data = new ArrayList();
		if(result==null)
		{
			Toast.makeText(getBaseContext(),"Please try again with correct location"  , Toast.LENGTH_SHORT).show();
	        
			event_data = new ArrayList();
			Intent i = new Intent(Feed.this, MainActivity.class);
	        startActivity(i);
	        finish();
	        
		}
		else
		{
		if(result.substring(0, 3)=="<br>")
		{  Toast.makeText(getBaseContext(),"Check connectivity ... Please try again"  , Toast.LENGTH_SHORT).show();
        
        Intent i = new Intent(Feed.this, MainActivity.class);
        startActivity(i);
        finish();
        event_data = new ArrayList();
		}
		else if(result.substring(0, 10)=="\"events\":");
		{
		getFromNet();
		}
		}
//////////set adapter to listview - event
	//	event_data = new Event[]{new Event(R.drawable.ic_launcher, "title")};
		 
					
					adapter = new EventAdapter(this,event_data);
		        
		        
		        eventList = (ListView)findViewById(R.id.eventlist);
		         
		        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
		   //     eventList.addHeaderView(header);
		        
		        eventList.setAdapter(adapter);
//////////
		       
//adapter.notifyDataSetChanged();	

		       
		        
		        /////event click
		        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				    @Override
				    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
				    
				    	ItemLocStatic = (Event) event_data.get(pos);
					if(!ItemLocStatic.loc.equalsIgnoreCase("no"))
					{
				    	String[] l=ItemLocStatic.loc.split("/");
						
						CameraPosition cameraPosition = new CameraPosition.Builder()
							.target(new LatLng(Double.parseDouble(l[0]),Double.parseDouble(l[1]))).zoom(15).build();

					map.animateCamera(CameraUpdateFactory
							.newCameraPosition(cameraPosition));
				    }
				    }
					
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		 MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.searchview_in_menu1, menu);
	        MenuItem searchItem = menu.findItem(R.id.action_search);
	        mSearchView = (SearchView) searchItem.getActionView();
	        setupSearchView(searchItem);

		return true;
		
	}
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
	
 	   
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        //mStatusView.setText("Query = " + query + " : submitted");
		int[] u=new int[event_data_backup.size()];
		String k=query;
		for(int l=0;l<event_data_backup.size();l++)
		{
			
			Event Item = (Event) event_data_backup.get(l);
			 //System.out.println(Item.title);
			
			if(Item.title.contains(k))
			{
				System.out.println("found match");
				u[l]=1;
			}
			else if(Item.org.contains(k))
			{
				u[l]=1;
			}
			else if(Item.description.contains(k))
			{
				u[l]=1;
			}
			else
			{
				u[l]=-1;
			}
		}//for
		
		ArrayList event_data_search;
		event_data_search= new ArrayList();
		for(int l=0;l<event_data_backup.size();l++)
		{
			if(u[l]==1)
			{
				Event Itemxy = (Event) event_data_backup.get(l);
				
				System.out.println("adding to list Itemxy"+Itemxy.title);
				event_data_search.add(Itemxy);
			}
		}
		event_data.clear();
		event_data.addAll(event_data_search);
		
		
		adapter.notifyDataSetChanged();
		new Thread() {

			public void run() {

			try{

			
				
			} catch (Exception e) {

			Log.e("tag", e.getMessage());

			}
			}
			}.start();
        return false;
    }

    public boolean onClose() {
        //mStatusView.setText("Closed!");
    		return false;
    }

    protected boolean isAlwaysExpanded() {
        return false;
    }
	public void getFromNet()
	{
		
	
		//String resultTrim=result.substring(10);      
		String resultTrim=result.substring(10,result.length());
		//System.out.println(resultTrim.substring(41600-1));
		result=resultTrim;
		try{
			JSONObject json_data = null;
			JSONObject json_org = null;
			JSONObject json_venue = null;
			System.out.println("in 1.5 ="+result.length());
				
				JSONArray jArray = new JSONArray(result);	
				System.out.println("inside  2.5");
				int y = jArray.length();
			  System.out.println("length = "+y);
				
			  int k=0;
			  String val;
			  System.out.println("object = "+jArray.getJSONObject(0));
				
			 // JSONObject json_name = jArray.getJSONObject(0).getJSONObject("summary").getJSONObject("filters");
			  //System.out.println(json_name.get("last_event"));
			  
			  //System.out.println(json_name.get("summary"));
			// System.out.println(json_name.get("city"));
			 
			 event_data = new ArrayList();
			 
			 String title=null;
			 String logo = null;
			 String org=null;
			 String date=null;
			 String description=null;
			 String loc=null;
			 String venueName;
			 String venueCity;
			 String address;
			 String address_2;
			 String url=null;
			  for(k=0;k<y-1;k++)
				{ 
			
				//	System.out.println("inside loop"+k);
				  if(jArray.getJSONObject(k+1).has("event"))
				    json_data = jArray.getJSONObject(k+1).getJSONObject("event");
				  if(jArray.getJSONObject(k+1).getJSONObject("event").has("organizer"))
				    json_org = jArray.getJSONObject(k+1).getJSONObject("event").getJSONObject("organizer");
				  if(jArray.getJSONObject(k+1).getJSONObject("event").has("venue"))
				    json_venue = jArray.getJSONObject(k+1).getJSONObject("event").getJSONObject("venue");
					//JSONObject json_ticket = jArray.getJSONObject(k+1).getJSONObject("event").getJSONObject("tickets");
					
					System.out.println("ff1");	
					//val=Integer.parseInt(json_data.getString("sno"));
					//String value=json_data.getString("locale");
					//System.out.println(value);
					
					if(json_data.has("title"))
						title=json_data.getString("title");
					else
						title="no title";
					//System.out.println("org name :"+ json_org.getString("name"));
					//System.out.println("venue :"+ json_venue.getString("name"));
					//System.out.println("price :"+ json_ticket.getString("price"));
					if(json_data.has("logo"))
					logo=json_data.getString("logo");
					else
						logo="http://www.paulmccloskeyart.com/images/light-grey-square.jpg";
					//System.out.println("url :"+ json_data.getString("url"));
					//System.out.println("title :"+ json_data.getString("title"));
					//System.out.println("description :"+ json_data.getString("description"));
					
					if(json_org.has("name"))
						org=json_org.getString("name");
					else
						org="";
					if(json_data.has("start_date"))
						date=json_data.getString("start_date");
					else
						date="";
					if(json_data.has("description"))
						description=json_data.getString("description");
					else
						description="";
					if(json_venue.has("name"))
						venueName=json_venue.getString("name");
					else
						venueName="";
					if(json_venue.has("Lat-Long"))
						loc=json_venue.getString("Lat-Long");
					else
						loc="no";
					if(json_venue.has("address"))
						address=json_venue.getString("address");
					else
						address="";
					if(json_venue.has("address_2"))
						address_2=json_venue.getString("address_2");
					else
						address_2="";
					if(json_venue.has("city"))
						venueCity=json_venue.getString("city");
					else
						venueCity="";
					if(json_data.has("url"))
						url=json_data.getString("url");
					else
						url="no link available";
					//System.out.println("loc : "+json_venue.getString("Lat-Long"));
					event_data.add(new Event(logo,title,org,date,description,venueName+"|"+address+"|"+address_2,loc,url));

					if(!loc.equalsIgnoreCase("no"))
					{
						String[] l=loc.split("/");
					
					MarkerOptions marker;
if(mapProper!=-1)
{
                 	marker = new MarkerOptions().position(
     						new LatLng(Double.parseDouble(l[0]),Double.parseDouble(l[1])))
     						.title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
     						//.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker));
     						;
     				map.addMarker(marker);
     				 marker.snippet(venueName);
}
					}
				}
				 event_data_backup = new ArrayList();
				 event_data_backup.addAll(event_data);			  
	//adapter.notifyDataSetChanged();
			  //	rowItems.add(item);
					//itemArrey.add(k,json_data.getString("routeNo")+"(offline)");
				 if(event_data.size()!=0)
					ItemLocStatic=(Event)event_data.get(0);
					
				if(mapProper!=-1)
				{
				if(event_data.size()!=0)
				{
					 Event ItemLoc = (Event) event_data.get(0);
					if(!ItemLoc.loc.equalsIgnoreCase("no"))
					{
				    	String[] l=ItemLoc.loc.split("/");
						
						CameraPosition cameraPosition = new CameraPosition.Builder()
							.target(new LatLng(Double.parseDouble(l[0]),Double.parseDouble(l[1]))).zoom(8).build();

					map.animateCamera(CameraUpdateFactory
							.newCameraPosition(cameraPosition));
				    }
				}
				}
			} catch (JSONException e) {System.out.println("caught");
			    Toast.makeText(getBaseContext(),"Please try again with proper location"  , Toast.LENGTH_SHORT).show();
	           
	           Intent i = new Intent(Feed.this, MainActivity.class);
	           startActivity(i);
	           finish();
		     
		 }

	}
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	  System.out.println("back Pressed");
	  Intent i = new Intent(Feed.this, MainActivity.class);
      startActivity(i);
      finish();
	}
	

	
}
	