package com.pesit.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashScreen extends Activity {
	private ProgressDialog progressDialog;
	public static String key;
	public static String E;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		RelativeLayout root=(RelativeLayout)findViewById(R.id.root);
		
		setTitle("");
		
		String head="Events";
		String load="loading events in "+key+" ...";
		 //conferences, conventions, entertainment, fundraisers, meetings, other, performances, reunions, sales, seminars, social, sports, tradeshows, travel, religion, fairs, food, music, recreation
		if(MainActivity.choice==1)
			{
			root.setBackgroundResource(R.drawable.fblue);
			head="All category";
			E="";
			}
		else if(MainActivity.choice==10)
			{
			root.setBackgroundResource(R.drawable.fconf);
			head="Conferences";
			E="conferences";
			}
		else if(MainActivity.choice==11)
			{
			root.setBackgroundResource(R.drawable.fmeet);
			head="Meetings";
			E="meetings";
			
			}
		else if(MainActivity.choice==12)
			{
			root.setBackgroundResource(R.drawable.fente);
			head="Entertainment";
			E="entertainment";
			}
		else if(MainActivity.choice==13)
			{
			root.setBackgroundResource(R.drawable.ffund);
			head="Fundraisers";
			E="fundraisers";
			}
		else if(MainActivity.choice==14)
			{
			root.setBackgroundResource(R.drawable.fconvn);
			head="Conventions";
			E="conventions";
			}
		else if(MainActivity.choice==15)
		{
		root.setBackgroundResource(R.drawable.fperf);
		head="performances";
		E="performances";
		}
		else if(MainActivity.choice==16)
		{
		root.setBackgroundResource(R.drawable.freun);
		head="Reunions";
		E="reunions";
		}
		
		else if(MainActivity.choice==17)
		{
		root.setBackgroundResource(R.drawable.fsale);
		head="Sales";
		E="sales";
		}
		else if(MainActivity.choice==18)
		{
		root.setBackgroundResource(R.drawable.fsemi);
		head="Seminars";
		E="seminars";
		}
		else if(MainActivity.choice==19)
		{
		root.setBackgroundResource(R.drawable.fsoci);
		head="Social";
		E="social";
		}
		else if(MainActivity.choice==20)
		{
		root.setBackgroundResource(R.drawable.fspor);
		head="Sports";
		E="sports";
		}
		else if(MainActivity.choice==21)
		{
		root.setBackgroundResource(R.drawable.ftrad);
		head="Tradeshows";
		E="Tradeshows";
		}
		else if(MainActivity.choice==22)
		{
		root.setBackgroundResource(R.drawable.ftrav);
		head="Travel";
		E="travel";
		}
		else if(MainActivity.choice==23)
		{
		root.setBackgroundResource(R.drawable.freli);
		head="Religion";
		E="religion";
		}
		else if(MainActivity.choice==24)
		{
		root.setBackgroundResource(R.drawable.ffair);
		head="Fairs";
		E="fairs";
		}
		else if(MainActivity.choice==25)
		{
		root.setBackgroundResource(R.drawable.ffood);
		head="Food";
		E="food";
		}
		
		else if(MainActivity.choice==26)
		{
		root.setBackgroundResource(R.drawable.fmusi);
		head="Music";
		E="music";
		}
		else if(MainActivity.choice==27)
		{
		root.setBackgroundResource(R.drawable.frecr);
		head="Recreation";
		E="frecreation";
		}
		root.setBackgroundResource(R.drawable.red);
		progressDialog = ProgressDialog.show(SplashScreen.this, head, load);

		new Thread() {

		public void run() {

		try{

		net();

		} catch (Exception e) {

		Log.e("tag", e.getMessage());
		 progressDialog.dismiss();
         MainActivity.T="Please Try again";
         System.out.print("kkkkkkkkkkkkkkkk");
         //Intent i = new Intent(SplashScreen.this, MainActivity.class);
         //startActivity(i);
         finish();
         
		}

		// dismiss the progress dialog

		progressDialog.dismiss();
        Intent i = new Intent(SplashScreen.this, Feed.class);
        startActivity(i);
        finish();
		}

		}.start();	

       

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
public void net()
{
	InputStream is = null;
	try {
		 HttpClient httpclient = new DefaultHttpClient();
	    //HttpPost httppost = new HttpPost("http://kidiyoor.site88.net/PESUbt/routesAvailable.php");
		 HttpPost httppost = new HttpPost("http://kidiyoor.site88.net/eventbrite/callweb.php?location="+key+"&cat="+E+"&org=&key=");
		// HttpPost httppost = new HttpPost("http://kidiyoor.site88.net/eventbrite/data/readData.php");
		// HttpPost httppost = new HttpPost("http://kidiyoor.site88.net/eventbrite/data/dataEB.json");
	    System.out.println("connect to website first time");
	     // Add your data
       // List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
         //   nameValuePairs.add(new BasicNameValuePair("username", username));
          //  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    // Execute HTTP Post Request
	   
        HttpResponse response = httpclient.execute(httppost);
       System.out.println("1");
       HttpEntity entity = response.getEntity();
      System.out.println("..............2");
	     is = entity.getContent();
	    System.out.println("..............3");
    } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
    	System.out.println("..............cTCH");
    	 progressDialog.dismiss();
    	 MainActivity.T="Please Try again";
         //Intent i = new Intent(SplashScreen.this, MainActivity.class);
         //startActivity(i);
    	 super.onBackPressed();
    	 finish();
         
    } catch (IOException e) {
        // TODO Auto-generated catch block
    	System.out.println("..............catch");
    	 progressDialog.dismiss();
    	 MainActivity.T="Please Try again";
         //Intent i = new Intent(SplashScreen.this, MainActivity.class);
         //startActivity(i);
         finish();
         super.onBackPressed();
    }

System.out.println("in download");
Feed.result = null;
try{
           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
           StringBuilder sb = new StringBuilder();
           String line = null;
           int y=0;
           while ((line = reader.readLine()) != null&&y==0) {
                   sb.append(line + "\n");y=1;
                  
           }
        
           is.close();
           Feed.result  = sb.toString();
          
           System.out.println("end of first half");
   }catch(Exception e){
           Log.e("log_tag", "Error converting result "+e.toString());
           System.out.println("caught");
           progressDialog.dismiss();
          
           MainActivity.T="Please Try again"; 
          // Intent i = new Intent(SplashScreen.this, MainActivity.class);
           //startActivity(i);
           super.onBackPressed();

           finish();
             }
}

}
