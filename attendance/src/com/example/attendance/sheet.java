package com.example.attendance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;





import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class sheet extends Activity implements OnClickListener{

	Button g;
	String pri = "";
	TextView tv;
	boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sheet);
		
		g = (Button)findViewById(R.id.bgenerate);
		tv = (TextView)findViewById(R.id.tvshowall);
		
		pri = att.ans;
		
		g.setOnClickListener(this);
		
		tv.setText(att.ans);
	}

	@Override
	public void onClick(View arg0) {
		
		
		String sFileName = "student.txt";
		 checkExternalMedia();
		 
		 
		 if( mExternalStorageAvailable == true && mExternalStorageWriteable == true )
		 {
			 
			 
			 File root = Environment.getExternalStorageDirectory();
			 File dir = new File (root.getAbsolutePath());
			    dir.mkdirs();
			    File file = new File(dir, "Attendance.txt");
			    
			    try {
			        FileOutputStream f = new FileOutputStream(file);
			        //PrintWriter pw = new PrintWriter(f);
			        //pw.println(""+sBody);
			        //pw.println("Hello");
			        //pw.flush();
			        //pw.close();
			        f.write(pri.getBytes());
			        f.close();
			        Toast t = Toast.makeText(sheet.this, "Saved", Toast.LENGTH_SHORT);
			        t.show();
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			        Toast t = Toast.makeText(sheet.this, "error", Toast.LENGTH_LONG);
			    	t.show();
			        //Log.i(TAG, "******* File not found. Did you" +
			          //      " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			 
		 }
		 
		 
		
	}
	
	private void checkExternalMedia(){
	      mExternalStorageAvailable = false;
	      mExternalStorageWriteable = false;
	    String state = Environment.getExternalStorageState();

	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        // Can read and write the media
	        mExternalStorageAvailable = mExternalStorageWriteable = true;
	    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        // Can only read the media
	        mExternalStorageAvailable = true;
	        mExternalStorageWriteable = false;
	    } else {
	        // Can't read or write
	        mExternalStorageAvailable = mExternalStorageWriteable = false;
	    }   
	    
	}
	
	

}
