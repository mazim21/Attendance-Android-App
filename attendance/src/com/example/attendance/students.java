package com.example.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View.OnClickListener;

public class students {
	
	public static final String KEY_NAME = "name";
	public static final String KEY_ID = "id";
	public static final String KEY_ATTENDED = "attended";
	public static final String KEY_TOTAL = "total";
	public static final String KEY_PERCENTAGE = "percentage";
	public static final String KEY_MARKS = "marks";
	
	private static final String DATABASE_NAME = "students";
	private static final String DATABASE_TABLE = "mis";
	private static final int DATABASE_VERSION = 1;
	
	private helper ourhelper2;
	private final Context ourcontext2;
	private SQLiteDatabase ourdatabase2;
	
	private static class helper extends SQLiteOpenHelper{

		public helper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +  
					KEY_ID + " TEXT PRIMARY KEY, " +					
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_ATTENDED + " INTEGER NOT NULL, " +
					KEY_TOTAL + " INTEGER NOT NULL, " +
					KEY_MARKS + " INTEGER NOT NULL, " +
					KEY_PERCENTAGE + " INTEGER NOT NULL);" 
					);
			
		}

		
		public void onUpgrade(SQLiteDatabase db1, int arg1, int arg2) {
			db1.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db1);
			
		}
		
	}
	
	public students(Context c){
		
		ourcontext2 = c;
	}
	

	

	public students open()throws SQLException{
		
		ourhelper2 = new helper(ourcontext2);
		ourdatabase2 = ourhelper2.getWritableDatabase();
		return this;
		
	}
	public void close(){
		
		ourhelper2.close();
	}
	
	public long create(String name, String reg, int l1, int l2,int m) {
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID,reg);
		cv.put(KEY_NAME, name);
		cv.put(KEY_ATTENDED, l1);
		cv.put(KEY_TOTAL, l2);
		int l3 = (l1 * 100) / l2;
		cv.put(KEY_PERCENTAGE, l3);
		cv.put(KEY_MARKS,m);
		
		return ourdatabase2.insert(DATABASE_TABLE, null, cv);
		
	}

	public String get() {
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int name = c.getColumnIndex(KEY_ID);
		int reg = c.getColumnIndex(KEY_NAME);
		int m = c.getColumnIndex(KEY_MARKS);
				int perk = c.getColumnIndex(KEY_PERCENTAGE);
		
		
		
		
		
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext() ){
			int a = c.getColumnIndex(KEY_ATTENDED);
			int t = c.getColumnIndex(KEY_TOTAL);
			
			String ww = c.getString(a);
			String qq = c.getString(t);
			
			int att = Integer.parseInt(ww);
			int tot = Integer.parseInt(qq);
			
			ContentValues cv = new ContentValues();
			
			int l3 = (att * 100) / tot;
			cv.put(KEY_PERCENTAGE, l3);
			
			String s = c.getString(name);
			
			s = "\'" + s + "\'";
			
			ourdatabase2.update(DATABASE_TABLE, cv,  KEY_ID + "=" + s, null);
			
			result = result + c.getString(name) + "       " + c.getString(reg) + "       " + c.getString(perk)+"%" +"        "+ c.getString(m) +"\n\n";
		}
		return result;
	}
	
	
	public String getDebarred() {
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, KEY_PERCENTAGE + "<" + 75, null, null, null, null);
		String res = "";
		
		int name = c.getColumnIndex(KEY_ID);
		int reg = c.getColumnIndex(KEY_NAME);
		int perk = c.getColumnIndex(KEY_PERCENTAGE);
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext() ){
			if(75 > c.getInt(4))
			res = res + c.getString(name) + "       " + c.getString(reg) + "       " + c.getString(perk)+"%" +"\n\n";
		}
		return res;
	}

	public void delete(String sid) throws SQLException{
		
		sid = "\'" + sid + "\'";
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		
		ourdatabase2.delete(DATABASE_TABLE, KEY_ID + "=" + sid,null);
		
	}

	public int getReg(String current) {
		// TODO Auto-generated method stub
		current = "\'" + current + "\'";
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, KEY_ID + "=" + current, null, null, null, null);
		int rs = 0;
		int gg = c.getColumnIndex(KEY_ID);
		int kk = c.getColumnIndex(KEY_ATTENDED);
			
		
		rs = 0;
			
			if( c.moveToFirst() )
			//if(current.matches(c.getString(gg)))
			{ 
				rs = 1;
				String hh = c.getString(kk);
				int dd = Integer.parseInt(hh);
				dd = dd + 1;
				
				String[] columns1 = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE};
				
				
				ContentValues cv = new ContentValues();
				
				cv.put(KEY_ATTENDED, dd);
				ourdatabase2.update(DATABASE_TABLE, cv,  KEY_ID + "=" + current, null);	
				
				//return dd;
				//increment_attend(dd, current);
				
				
			}
		
		
		return rs;
		
		
	}




	private void increment_attend(int dd, String current) {
		// TODO Auto-generated method stub
		current = "\'" + current + "\'";
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE};
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, KEY_ID + "=" + current, null, null, null, null);
		
		ContentValues cv = new ContentValues();
		c.moveToFirst();
		cv.put(KEY_ATTENDED, dd);
		ourdatabase2.update(DATABASE_TABLE, cv,  KEY_ID + "=" + current, null);	
	}




	public void getTotal() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		
		int tt = c.getColumnIndex(KEY_TOTAL);
		int dd = c.getColumnIndex(KEY_ID);
		
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext() ){
			
			ContentValues cv = new ContentValues();
			String yy = c.getString(dd);
			String hh = c.getString(tt);
			int tot = Integer.parseInt(hh);
			tot = tot + 1;
			
			yy = "\'" + yy + "\'";
			
			
			cv.put(KEY_TOTAL, tot);
			ourdatabase2.update(DATABASE_TABLE, cv, KEY_ID + "=" + yy, null);
		}
		
	}




	public int avg() {
		
		String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		int tt = c.getColumnIndex(KEY_MARKS);
		
		int sum = 0;
		
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext() ){
			
			String hh = c.getString(tt);
			int tot = Integer.parseInt(hh);
			sum = sum + tot;
			
		}
		
		return sum;
	}




	public int getcount() {
		
String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ATTENDED,KEY_TOTAL,KEY_PERCENTAGE,KEY_MARKS};
		
		Cursor c = ourdatabase2.query(DATABASE_TABLE, columns, null, null, null, null, null);
		 
		int x = 0;
		
for(c.moveToFirst(); !c.isAfterLast();c.moveToNext() ){
			
			x++;
			
		}
		
		if( x == 0 )
			return 1;
		
		return x;
	}

}
