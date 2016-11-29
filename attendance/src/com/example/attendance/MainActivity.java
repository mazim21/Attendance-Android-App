package com.example.attendance;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 Thread timer = new Thread()
		 {
			 public void run(){
					
					try{
						
						sleep(2000);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
					finally{
						Intent intent1 = new Intent(MainActivity.this,home.class);
				        startActivity(intent1);
				 
					} 
			 }
		 };
		 timer.start();
	
}
	 protected void onPause() {
			super.onPause();
			finish();
		}
}
	
