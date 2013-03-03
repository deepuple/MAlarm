package com.example.malarm;

import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MActivity extends Activity {

	private BroadcastReceiver receiver = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ToggleButton btn = (ToggleButton)findViewById(R.id.toggleButton1);
        
        receiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO : noti Register
				String name = intent.getAction();
				//if(name.equals("arabiannight.tistory.com.sendreciver.gogogo")){
				//	Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
				//}
				Log.d("INFO","intent name["+intent.getPackage()+"]");
			}
		};
		
        btn.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick( View v ) {  
        		//Receiver µî·Ï
        		if(btn.isChecked()){
        			IntentFilter filter = new IntentFilter();//CONNECTIVITY_CHANGE);
        			filter.addCategory("android.intent.category.DEFAULT");
        			registerReceiver(receiver, filter);
        		}
        		else
        			unregisterReceiver(receiver);
        	}
        });   
        //serviceList();
        packageList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void serviceList() {
    	ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
    	List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(100);
    	
    	for(int i=0; i<rs.size(); i++){
    		ActivityManager.RunningServiceInfo rsi = rs.get(i);
    		Log.d("run service","Package Name : " + rsi.service.getPackageName());
    	}
    }
    
    private void packageList() {
    	PackageManager pm = getPackageManager();
    	Intent intent = new Intent(Intent.ACTION_MAIN, null);
    	intent.addCategory(Intent.CATEGORY_LAUNCHER);
    	List<ResolveInfo> groupApps = pm.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
    	Collections.sort(groupApps, new ResolveInfo.DisplayNameComparator(pm));
  
		//Log.d("TEST","Device Name["+android.os.Build.DEVICE+"]");
		  
		for(int i=0; i<groupApps.size(); i++){
			Log.d("INFO","PakageName["+groupApps.get(i).activityInfo.applicationInfo.packageName+"]");
			Log.d("INFO","ClassName["+groupApps.get(i).activityInfo.name+"]");
			//Log.d("INFO","IntentFilter["+groupApps.get(i).activityInfo.applicationInfo.+"]"); 
		};
    }
    
    private void packageInfo() {
    	PackageManager pm = getPackageManager();
    	
    }
}
