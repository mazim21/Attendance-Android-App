package com.example.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class viewstduents extends Activity implements OnClickListener{

	TextView t;
	Button b,av;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewstudent);
		
		t = (TextView)findViewById(R.id.tvView);
		b = (Button)findViewById(R.id.bDebarred);
		av = (Button)findViewById(R.id.bavg);
		
		b.setOnClickListener(this);
		av.setOnClickListener(this);
		students s = new students(this);
		s.open();
		String a = s.get();
		s.close();
		t.setText(a);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bDebarred:
			Intent open = new Intent("com.example.hcompanion.DEBARRED");
			startActivity(open); 
			break;
			
		case R.id.bavg:
			
			students s = new students(this);
			s.open();
			int x = s.avg();
			
			int y = s.getcount();
			
			int z;
			
			z = x / y;
			t.setText("Class Average : "+z);
			
			s.close();
			
			break;
		}
		
	}
	
	

}
