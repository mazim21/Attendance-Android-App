package com.example.attendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class home extends FragmentActivity{
	
	ViewPager viewpager = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		viewpager = (ViewPager)findViewById(R.id.pager);
		
		FragmentManager fragmmentmmanager = getSupportFragmentManager();
		
		
		viewpager.setAdapter(new Myadapter( fragmmentmmanager));
		
	}

}

class Myadapter extends FragmentPagerAdapter
{

	public Myadapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int i) {
		
		Fragment fragment = null;
		
		if( i == 0)
		{
			fragment = new att();
		}
		
		if( i == 1)
		{
			fragment = new manage();
		}
		
		
		return fragment;
	}

	@Override
	public int getCount() {
		
		return 2;
	}
	
	public CharSequence getPageTitle(int p){
	
		String title = new String(); 
		
		if(p == 0)
		{
			return "Take Attendance";
		}
		if( p == 1)
		{
			
			return "Manage Database";
		}
		return null;
		
		
	}
	
}
