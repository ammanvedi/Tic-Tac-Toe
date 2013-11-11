package com.comp1008.AmmanVedi.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DifficultySelect extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_difficulty_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_difficulty_select, menu);
		return true;
	}
	
	public void startEasy(View v)
	{
		
		AIDrawView dv = new AIDrawView(this, 1);
		this.setContentView(dv);
		dv.requestFocus();
		
	}
	
	public void startMedium(View v)
	{
		AIDrawView dv = new AIDrawView(this, 2);
		this.setContentView(dv);
		dv.requestFocus();
	}
	
	public void startHard(View v)
	{
		AIDrawView dv = new AIDrawView(this, 3);
		this.setContentView(dv);
		dv.requestFocus();
	}
	
	public void showHighScores(View v)
	{
		Intent intentHS = new Intent(this, HighScores.class);
		startActivity(intentHS);
	}

}
