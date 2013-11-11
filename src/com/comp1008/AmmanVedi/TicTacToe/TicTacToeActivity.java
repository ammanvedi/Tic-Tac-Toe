package com.comp1008.AmmanVedi.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class TicTacToeActivity extends Activity {
	
	//private Context cxt = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.activity_tic_tac_toe);
		getWindow().setBackgroundDrawableResource(R.drawable.bg);
		
		//load the next view after 5 seconds
		
    	
        super.onCreate(savedInstanceState);		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                go();

		            }
		        }, 
		        5000 
		);

    	}
	

	public void go()
	{
	
	Intent intent = new Intent(this, SplashScreen.class);
	System.out.println("here");
	startActivity(intent);
	}


	}

