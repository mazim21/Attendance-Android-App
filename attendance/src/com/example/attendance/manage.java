package com.example.attendance;




import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class manage extends Fragment{
	
	
	static int count = 0;
	Button add,view,del;
	boolean diditwork;
	TextView n,r,a,t;
	EditText name,reg,att,tot,mark;
	RelativeLayout mLinearLayout2;
	
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		/*setContentView(R.layout.database);
		
		add = (Button) findViewById(R.id.button1);
		view = (Button) findViewById(R.id.button2);
		del = (Button) findViewById(R.id.button3);
		
		n = (TextView)findViewById(R.id.textView1);
		r = (TextView)findViewById(R.id.textView2);
		
		name = (EditText)findViewById(R.id.editText1);
		reg = (EditText)findViewById(R.id.editText2);
		
		add.setOnClickListener(this);
		view.setOnClickListener(this);
		del.setOnClickListener(this);
		*/
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mLinearLayout2 = (RelativeLayout) inflater.inflate(R.layout.database,
                container, false);
		
		add = (Button) mLinearLayout2.findViewById(R.id.button1);
		view = (Button) mLinearLayout2.findViewById(R.id.button2);
		del = (Button) mLinearLayout2.findViewById(R.id.button3);
		
		//n = (TextView)mLinearLayout2.findViewById(R.id.textView1);
		/*r = (TextView)mLinearLayout2.findViewById(R.id.textView2);
		a = (TextView)mLinearLayout2.findViewById(R.id.tvAttend);
		t = (TextView)mLinearLayout2.findViewById(R.id.tvTotal);*/
		
		name = (EditText)mLinearLayout2.findViewById(R.id.editText1);
		reg = (EditText)mLinearLayout2.findViewById(R.id.editText2);
		att = (EditText)mLinearLayout2.findViewById(R.id.etAttend);
		tot = (EditText)mLinearLayout2.findViewById(R.id.etTotal);
		mark = (EditText)mLinearLayout2.findViewById(R.id.EditText01);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				diditwork = true;
				try{
				String na = name.getText().toString();
				String num = reg.getText().toString();
				String aa = att.getText().toString();
				String tt = tot.getText().toString();
				String ma = mark.getText().toString();
				
				
				int l1 = Integer.parseInt(aa);
				int l2 = Integer.parseInt(tt);
				int m = Integer.parseInt(ma);
				
				if( m > 50 )
					m = 0;
				
				students s = new students(getActivity());
				s.open();
				if( !na.matches("") && !num.matches("") && l1<=l2 )
				s.create(na, num, l1, l2,m);
				count++;
				s.close();
				}
				catch (Exception e) {
					
					diditwork = false;
					String error = e.toString();
					Dialog d = new Dialog(getActivity());
					d.setTitle("ERROR!");
					TextView tv = new TextView(getActivity());
					tv.setText("Please enter Valid Item Id");
					d.setContentView(tv);
					d.show();
					
				}
				finally
				{
					if(diditwork == true)
					{
				Dialog d1 = new Dialog(getActivity());
				d1.setTitle("DATABASE UPDATED!");
				TextView tv1 = new TextView(getActivity());
				tv1.setText("Success");
				d1.setContentView(tv1);
				d1.show();
				name.setText("");
				reg.setText("");
				att.setText("");
				tot.setText("");
				mark.setText("");
					}
					}
				
			}
		});
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent i = new Intent(getActivity(),viewstduents.class);
				startActivity(i);
				
			}
		});
		
		del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				try{
					String sid = reg.getText().toString();
					students ex = new students(getActivity());
					ex.open();
					ex.delete(sid);
					ex.close();
					Dialog de = new Dialog(getActivity());
					de.setTitle("Deleted!!");
					de.show();
					reg.setText("");
					}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		});
		
		return mLinearLayout2;
		
		//return super.onCreateView(inflater, container, savedInstanceState);
	}

	
	/*
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.button1:
			//add
			diditwork = true;
			try{
			String na = name.getText().toString();
			String num = reg.getText().toString();
			students s = new students(this);
			s.open();
			if( !na.matches(" ") && !num.matches(" ") )
			s.create(na, num);
			s.close();
			}
			catch (Exception e) {
				
				diditwork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("ERROR!");
				TextView tv = new TextView(this);
				tv.setText("Please enter Valid Item Id");
				d.setContentView(tv);
				d.show();
				
			}
			finally
			{
				if(diditwork == true)
				{
			Dialog d1 = new Dialog(this);
			d1.setTitle("DATABASE UPDATED!");
			TextView tv1 = new TextView(this);
			tv1.setText("Success");
			d1.setContentView(tv1);
			d1.show();
			name.setText("");
			reg.setText("");
				}
				}
			break;
			
		case R.id.button2:
			//view
			Intent i = new Intent(manage.this,viewstduents.class);
			startActivity(i);
			break;
			
		
		case R.id.button3:
			//del
			try{
				String sid = reg.getText().toString();
				students ex = new students(this);
				ex.open();
				ex.delete(sid);
				ex.close();
				Dialog de = new Dialog(this);
				de.setTitle("Deleted!!");
				de.show();
				reg.setText("");
				}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			break;
		
		}
		
	}*/
	
	

}
