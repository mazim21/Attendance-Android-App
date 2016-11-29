package com.example.attendance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Debarred extends Activity{
	
	TextView tt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debarred);
		
		tt = (TextView)findViewById(R.id.tvDebarred);
		
		students ss = new students(this);
		ss.open();
		String aa = ss.getDebarred();
		ss.close();
		tt.setText(aa);
		
	}
		
		
		
}
