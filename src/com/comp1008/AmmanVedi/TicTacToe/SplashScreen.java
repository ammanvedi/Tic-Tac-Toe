package com.comp1008.AmmanVedi.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SplashScreen extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash_screen);
		System.out.println("finished oncreate");
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{ 
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.activity_splash_screen, menu);
		return true;
	}

	public void startTwoPlayer(View v)
	{
		

			DrawView dv = new DrawView(this);
			this.setContentView(dv);
			dv.requestFocus();
		
	}
	
	public void startSinglePlayer(View v)
	{
		
		//bring up the difficulty select screen
			Intent intentDS = new Intent(this, DifficultySelect.class);
			startActivity(intentDS);
		
	}


}
