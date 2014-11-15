package com.pesit.event;
import com.google.android.gms.ads.*;
import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class Profile extends Activity {
	/** The view to show the ad. */
	  private AdView adView;
	 
	  /* Your ad unit id. Replace with your actual ad unit id. */
	  //private static final String AD_UNIT_ID = "ca-app-pub-7676423146760649/4947285613";
	  private static final String AD_UNIT_ID = "ca-app-pub-7676423146760649/5130475210";
	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ListView cards=(ListView)findViewById(R.id.listCard);
		ListView cards2=(ListView)findViewById(R.id.listCard2);
		ListView cards3=(ListView)findViewById(R.id.listCard3);

		ArrayList event_data;
		event_data = new ArrayList();
		event_data.add(new Event(Feed.ItemLocStatic.icon, Feed.ItemLocStatic.title,"","","","","",""));
		
		

		EventAdapter adapter = new EventAdapter(this,event_data);
		cards.setAdapter(adapter);
		ArrayList listData;
		
		listData = new ArrayList();
		listData.add(new profile_detail_class("Venue",Feed.ItemLocStatic.venue));
		
		
		Profile_detail_adapter pro_ada= new Profile_detail_adapter(this, listData);
		cards2.setAdapter(pro_ada);
		
		ArrayList listData3;
		listData3 = new ArrayList();
		listData3 = new ArrayList();
		listData3.add(new profile_detail_class("Description",convert(Feed.ItemLocStatic.description)));
		
		Profile_detail_adapter pro_ada2= new Profile_detail_adapter(this, listData3);
		cards3.setAdapter(pro_ada2);
		
		ListView cards4=(ListView)findViewById(R.id.listCard4);
		ArrayList listData4;
		listData4 = new ArrayList();
		listData4 = new ArrayList();
		listData4.add(new profile_detail_class("Organizaiton",Feed.ItemLocStatic.org));
		
		Profile_detail_adapter pro_ada4= new Profile_detail_adapter(this, listData4);
		cards4.setAdapter(pro_ada4);
		
		ListView cards5=(ListView)findViewById(R.id.listCard5);
		ArrayList listData5;
		listData5 = new ArrayList();
		listData5 = new ArrayList();
		
		listData5.add(new profile_detail_class("Date and Time",Feed.ItemLocStatic.date));
		
		Profile_detail_adapter pro_ada5= new Profile_detail_adapter(this, listData5);
		cards5.setAdapter(pro_ada5);
				
		//ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.titles, android.R.layout.simple_list_item_1);
		//cards2.setAdapter(aa);
		//cards2.setAdatper(new ArrayAdapter<String>(this, R.layout.some_layout_to_use, R.id.some_textview_in_layout, listData);
	ImageButton register;
	register=(ImageButton)findViewById(R.id.register);
	register.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Uri uri = Uri.parse(Feed.ItemLocStatic.url);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            // Create and start the chooser
            Intent chooser = Intent.createChooser(intent, "Open with");
            startActivity(intent);
		}
	});

	
	
	

	// Create an ad.
	
    
    // Insert the Ad Unit ID
    adView = new AdView(this);
    adView.setAdSize(AdSize.BANNER);
    adView.setAdUnitId(AD_UNIT_ID);
    AdRequest adRequest = new AdRequest.Builder()
   // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Emulator
   // .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4") // My Galaxy Nexus test phone
    .addTestDevice("D5F4A91730268449FEBBB51FFBF9EA39")
    .build();

    // Add the AdView to the view hierarchy. The view will have no size
    // until the ad is loaded.
    LinearLayout layout = (LinearLayout) findViewById(R.id.ads);
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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public String convert(String x)
    {int i=0;
    String result="";
    int u = 0;
    for (i = 0; i < x.length();i++)
    {
        if(x.charAt(i)!='<'&&x.charAt(i)!='>'&&u==0)
        result = result+x.charAt(i);

        if(x.charAt(i)=='<')
            u=1;
        if(x.charAt(i)=='>')
            u=0;

    }
	return result;
    }
	
	
	@Override
	  public void onResume() {
	    super.onResume();
	    if (adView != null) {
	      adView.resume();
	    }
	  }

	  @Override
	  public void onPause() {
	    if (adView != null) {
	      adView.pause();
	    }
	    super.onPause();
	  }

	  /** Called before the activity is destroyed. */
	  @Override
	  public void onDestroy() {
	    // Destroy the AdView.
	    if (adView != null) {
	      adView.destroy();
	    }
	    super.onDestroy();
	  }

}
