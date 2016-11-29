package com.example.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class First extends Activity implements OnClickListener{

	Button db,attendance;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		db = (Button)findViewById(R.id.bdb);
		attendance = (Button)findViewById(R.id.batt);
		
		db.setOnClickListener(this);
		attendance.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.batt:
			Intent j = new Intent(First.this,att.class);
			startActivity(j);
			break;
			
		case R.id.bdb:
			
			Intent i = new Intent(First.this,manage.class);
			startActivity(i);
			
			break;
		}
		
	}
	
	

}
