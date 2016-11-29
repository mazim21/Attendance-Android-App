package com.example.attendance;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class att extends Fragment {

	RadioGroup rg1,rg2;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	RadioButton rb1,rb2,rb3,rb4,rb5;
	TextView check,reg;
	Button gen,add,speak;
	static String ans = "";
	String current="";
	RelativeLayout mLinearLayout;
	
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.att);
		
		/*rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
		rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
		
		rb1 = (RadioButton)findViewById(R.id.radio0);
		rb2 = (RadioButton)findViewById(R.id.radio1);
		
		rb3 = (RadioButton)findViewById(R.id.radio00);
		rb4 = (RadioButton)findViewById(R.id.radio01);
		rb5 = (RadioButton)findViewById(R.id.radio02);
		
		reg = (TextView)findViewById(R.id.textView3);
		check = (TextView)findViewById(R.id.textView4);
		
		gen = (Button)findViewById(R.id.button3);
		add = (Button)findViewById(R.id.button2);
		speak = (Button)findViewById(R.id.button1); 
		
		gen.setOnClickListener(this);
		add.setOnClickListener(this);
		speak.setOnClickListener(this);
		*/
		
	}
	
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.att,
                container, false);
		
		
		rg1 = (RadioGroup)mLinearLayout.findViewById(R.id.radioGroup1);
		rg2 = (RadioGroup)mLinearLayout.findViewById(R.id.radioGroup2);
		
		rb1 = (RadioButton)mLinearLayout.findViewById(R.id.radio0);
		rb2 = (RadioButton)mLinearLayout.findViewById(R.id.radio1);
		
		rb3 = (RadioButton)mLinearLayout.findViewById(R.id.radio00);
		rb4 = (RadioButton)mLinearLayout.findViewById(R.id.radio01);
		rb5 = (RadioButton)mLinearLayout.findViewById(R.id.radio02);
		
		reg = (TextView)mLinearLayout.findViewById(R.id.textView3);
		check = (TextView)mLinearLayout.findViewById(R.id.textView4);
		
		gen = (Button)mLinearLayout.findViewById(R.id.button3);
		add = (Button)mLinearLayout.findViewById(R.id.button2);
		speak = (Button)mLinearLayout.findViewById(R.id.button1);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				students s = new students(getActivity());
				s.open();
				int aa = s.getReg(current);
				//String x = s.getReg(current);
				s.close();
				
				if(aa == 1)
				{
				ans = current + "\n\n" + ans ;
				reg.setText(current);
				check.setText("Match Found!");
				}
				else
					check.setText("Not Found!");
			}
		});
		
		speak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				promptSpeechInput();
				
			}
		});
		
		gen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
	            ans = ans + "\n\n"  + currentDateTimeString;
	            
	            students s = new students(getActivity());
				s.open();
				s.getTotal();
				s.close();
				
				 Intent intent = new Intent(getActivity(), sheet.class);
	                startActivity(intent);
				
			}
		});
		
		
		return mLinearLayout;
		
		//return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	 private void promptSpeechInput() {
	        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
	                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
	        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
	                getString(R.string.speech_prompt));
	        try {
	            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
	        } catch (ActivityNotFoundException a) {
	           /* Toast.makeText(getApplicationContext(),
	                    getString(R.string.speech_not_supported),
	                    Toast.LENGTH_SHORT).show();*/
	        }
	    }
	 
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	    
	        
	        switch (requestCode) {
	        case REQ_CODE_SPEECH_INPUT: {
	            if ( null != data) {           	
	       	            	
	                ArrayList<String> result = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	                
	                RadioButton ans1 = (RadioButton)mLinearLayout.findViewById(rg1.getCheckedRadioButtonId());
	        		String anstxt = ans1.getText().toString();
	        		
	        		RadioButton ans2 = (RadioButton)mLinearLayout.findViewById(rg2.getCheckedRadioButtonId());
	        		String anstxt2 = ans2.getText().toString();
	        		
	                reg.setText(anstxt + anstxt2 + result.get(0));
	                current = anstxt + anstxt2 + result.get(0);
	                
	            }
	            break;
	        }
	 
	        }
	    }
	 
	/*
	public void onClick(View v) {
		switch(v.getId()){
			
		case R.id.button1:
			//speak
			promptSpeechInput();
			break;
			
		case R.id.button2:
			//add
			ans = current + "\n\n" + ans ;
			reg.setText("");
			break;
			
		case R.id.button3:
			//gen
			String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            ans = ans + "\n\n"  +currentDateTimeString;
            
			Intent x = new Intent(att.this,sheet.class);
			startActivity(x);
			finish();
			break;
		}
		
	}*/
	
	

}
