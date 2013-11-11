package com.comp1008.AmmanVedi.TicTacToe;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import java.lang.Math;



public class DrawView extends View implements OnTouchListener
{

	private Paint text = new Paint();
	private Paint board = new Paint();
	private Paint blueX = new Paint();
	private Paint redO = new Paint();
	private Paint bgPaint = new Paint();
	private Rect[] sectors = new Rect[9]; 
	private float sectorsize;
	@SuppressWarnings("unused")
	private Tuple TurnTuple = new Tuple(-1, -1);
	private GameState State = new GameState();
	private int playernum = 1;
	private int touchx;
	private int touchy;


	public DrawView(Context context)
	{
		super(context);
		
		bgPaint.setColor(Color.rgb(255, 243, 71));
		text.setColor(Color.BLACK);
		text.setTextAlign(Align.CENTER);
		text.setTextSize(20);
		text.setAntiAlias(true);
		board.setColor(Color.BLACK);
		board.setStyle(Style.STROKE);
		redO.setColor(Color.RED);
		redO.setTextAlign(Align.CENTER);
		redO.setAntiAlias(true);
		blueX.setTextAlign(Align.CENTER);
		blueX.setColor(Color.BLUE);
		blueX.setAntiAlias(true);


		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);
	}

	@Override
	public void onDraw(Canvas canvas)
	{

		
		canvas.drawRect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), bgPaint);
		this.drawBoard(canvas);
		this.drawState(canvas, State);
		

		if(State.checkForWinner() == 1)
		{
			System.out.println("theres a winner, and its player : ");
			State.resetGameState();
			//invalidate();
		}else
		{
			System.out.println("theres no winner check full :(");
			if(State.checkFull())
			{
				System.out.println("theres no winner :(");
				State.resetGameState();
			}
		}


	}

	public boolean onTouch(View v, MotionEvent event)
	{   

		//NEEDED TO ALLOW RE DRAWING OF CANVAS	

		touchx = (int) event.getX();
		touchy = (int) event.getY();



		if(event.getAction() == MotionEvent.ACTION_DOWN){

			//check where the user clicked and then update the state accordingly

			for(int i = 0; i < 9; i++)
			{
				if(this.testContainment(sectors[i], touchx, touchy)){
					//TurnTuple.setTuple(1, i);
					if(State.Update(new Tuple(playernum, i)) == false){
						System.out.println("Failed to update dure to invalid move");
					}else
					{
						//succeeded
						//switch players

						if(playernum == 1){
							playernum = 2;
						}else
						{
							playernum = 1;
						}
					}

					System.out.println("Sector Selected: " + i + " By : Player " + playernum);

					break;
				}
			}


		}
		invalidate();
		return true;
	}


	public void drawX(Canvas cvs, int AtSector)
	{
		blueX.setTextSize(sectorsize);
		cvs.drawText("X", sectors[AtSector].centerX(), sectors[AtSector].centerY() + (int)((float)blueX.getTextSize() / 2.5), blueX);


	}

	public void drawO(Canvas cvs, int AtSector)
	{

		redO.setTextSize(sectorsize);
		cvs.drawText("O", sectors[AtSector].centerX(), sectors[AtSector].centerY() + (int)((float)redO.getTextSize() / 2.5), redO);



	}

	public void drawBoard(Canvas cvs)
	{


		//if the array is empty then generate rects and fill it
		if(sectors[1] == null){
			
			

			float thirdofheight = (this.getHeight() / 3);
			sectorsize = (thirdofheight / 3);
			int origin_x = 0, origin_y = 0;
			
			int offset_x = (int)((this.getWidth()/2) - ((sectorsize * 3) /2));
			//80 is the size of the scorebox
			int offset_y = (int)((this.getHeight()/2) - ((sectorsize * 3) /2) - 80);
			
			//create the bounds of the board
			@SuppressWarnings("unused")
			Rect bounds = new Rect(origin_x, (int)thirdofheight, (int)thirdofheight, origin_y);
			//cvs.drawRect(bounds, text);

			//fill the board with its sectors
			Rect sector1 = new Rect(origin_x, (int)(origin_y + sectorsize), (int)(origin_x + sectorsize), origin_y);
			Rect sector2 = new Rect((int)(origin_x + sectorsize), (int)(origin_y + sectorsize), (int)((origin_x + sectorsize)*2), origin_y);
			Rect sector3 = new Rect((int)((origin_x + sectorsize)*2), (int)(origin_y + sectorsize),  (int)((origin_x + sectorsize)*3), origin_y);

			Rect sector4 = new Rect(origin_x, (int)((origin_y + sectorsize)*2), (int)(origin_x + sectorsize), (int)((origin_y + sectorsize)));
			Rect sector5 = new Rect((int)(origin_x + sectorsize), (int)((origin_y + sectorsize)*2), (int)((origin_x + sectorsize)*2), (int)((origin_y + sectorsize)));
			Rect sector6 = new Rect((int)((origin_x + sectorsize)*2), (int)((origin_y + sectorsize)*2), (int)((origin_x + sectorsize)*3), (int)((origin_y + sectorsize)));

			Rect sector7 = new Rect(origin_x, (int)((origin_y + sectorsize)*3), (int)(origin_x + sectorsize), (int)((origin_y + sectorsize)*2));
			Rect sector8 = new Rect((int)(origin_x + sectorsize), (int)((origin_y + sectorsize)*3), (int)((origin_x + sectorsize)*2), (int)((origin_y + sectorsize)*2));
			Rect sector9 = new Rect((int)((origin_x + sectorsize)*2), (int)((origin_y + sectorsize)*3), (int)((origin_x + sectorsize)*3), (int)((origin_y + sectorsize)*2));

			sectors[0] = sector1;
			sectors[1] = sector2;
			sectors[2] = sector3;
			sectors[3] = sector4;
			sectors[4] = sector5;
			sectors[5] = sector6;
			sectors[6] = sector7;
			sectors[7] = sector8;
			sectors[8] = sector9;

			for(int x = 0; x < 9; x++)
			{
				sectors[x].offset(offset_x, offset_y);
			}

		}
		
		this.drawScore(cvs, sectors[1]);

		cvs.drawRect(sectors[0], board);
		cvs.drawRect(sectors[1], board);
		cvs.drawRect(sectors[2], board);
		cvs.drawRect(sectors[3], board);
		cvs.drawRect(sectors[4], board);
		cvs.drawRect(sectors[5], board);
		cvs.drawRect(sectors[6], board);
		cvs.drawRect(sectors[7], board);
		cvs.drawRect(sectors[8], board);



	}

	public void putText(Canvas cvs, int x, int y, String str)
	{
		cvs.drawText(str, x, y, text);
	}
	
	public void drawScore(Canvas cvs, Rect firstRect)
	{
		//passing the top left rect allows me to work out the co ordinates of where i want to put
		// the score display
		
		//offset from the bottom of the game board (adjustable)
		int off = 10;
		
		//calculate and draw the scorebox
		int bottom = (firstRect.bottom + (Math.abs(firstRect.height()) * 3) + off);
		int left = firstRect.left - Math.abs(firstRect.height());
		
		Rect scorebox = new Rect(left, (bottom + 80) , (firstRect.left +(firstRect.width() * 2)), bottom);
		cvs.drawRect(scorebox, board);
		
		//draw actual score values
		//putText(cvs, left + off , bottom + (int)text.getTextSize(), "Player 1 : " +  Integer.toString(State.getScore()[0]));
		//putText(cvs, left + off , bottom + (int)text.getTextSize() + 20, "Player 2 : " +  Integer.toString(State.getScore()[1]));
		
		putText(cvs, scorebox.centerX() , scorebox.centerY(), "Player 1 : " +  Integer.toString(State.getScore()[0]));
		putText(cvs, scorebox.centerX() , scorebox.centerY() + 25, "Player 2 : " +  Integer.toString(State.getScore()[1]));


		
	}

	public void drawState(Canvas cvs, GameState CurrentState)
	{
		//System.out.println("state: " + CurrentState.getRawState().toString());
		System.out.println("---------");
		System.out.println("| " + CurrentState.getRawState()[0][0] + " " + CurrentState.getRawState()[0][1] + " " + CurrentState.getRawState()[0][2] + " |");
		System.out.println("| " + CurrentState.getRawState()[1][0] + " " + CurrentState.getRawState()[1][1] + " " + CurrentState.getRawState()[1][2] + " |");
		System.out.println("| " + CurrentState.getRawState()[2][0] + " " + CurrentState.getRawState()[2][1] + " " + CurrentState.getRawState()[2][2] + " |");
		System.out.println("---------");
		//go through array drawing x's or o's

		for(int y = 0; y < 3; y++)
		{
			for(int z = 0; z < 3; z++)
			{
				if(CurrentState.getRawState()[y][z] == 1){
					//drawx
					this.drawX(cvs, CurrentState.indexToSector(y, z));
				}
				if(CurrentState.getRawState()[y][z] == 2){
					//draw o 
					this.drawO(cvs, CurrentState.indexToSector(y, z));
				}
			}
		}
		
		

	}

	public boolean testContainment(Rect r, int px, int py)
	{
		if(px > r.left && px < r.right)
		{
			if(py > r.bottom && py < r.top){
				return true;
			}
		}


		return false;
	}


}




