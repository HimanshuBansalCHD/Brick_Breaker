package BB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	
	private boolean play=true;
	private int score=0;
	
	private int totalbricks=21;
	
	private MapGenerator map;
	private Music music;
	
	private Timer timer;
	private int delay=8; 
	
	private int ballX=120;
	private int ballY=350;
	
	private int pedalX=310;
	
	private int ballXdir=-2;
	private int ballYdir=-1;
	
	GamePlay(){
		map=new MapGenerator(3,7);
		addKeyListener(this);
		timer = new Timer(delay,this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.white); //Background Color
		g.fillRect(0, 0, 700, 600); //Background Dimensions  
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.red);  //Set Border Color
		g.fillRect(0, 0, 3, 600);   //Color Left Vertical Border
		g.fillRect(0, 0, 700, 3);	//Color Upper Horizontal Border 
		g.fillRect(697,0,3,600);	//Color Right Vertical Border
		g.fillRect(0, 597, 700, 3);	//Color Bottom Horizontal Border
		
		g.setColor(Color.blue);	//Create Pedal
		g.fillRect(pedalX, 570, 100, 8);	//Color Pedal
		
		g.setColor(Color.green);
		g.fillOval(ballX, ballY, 15, 15);
		
		g.setColor(Color.green);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(" "+score , 590, 30);
		
		if(totalbricks<=0) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			
			g.setColor(Color.green);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Yeah!, You Won, Score: "+score , 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart: " , 230, 350);
		}
		
		if(ballY > 570) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Score: "+score , 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart: " , 230, 350);
			
		}
		
		
		g.dispose();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Actions are being assigned on every event here we'll perfome those actions
		
		timer.start();
		
		if(play) {
			if(new Rectangle(ballX,ballY,15,15).intersects(new Rectangle(pedalX,570,100,8))) {
				ballYdir = -ballYdir;
			}
			
			for(int i=0;i<map.mp.length;i++) {
				for(int j=0;j<map.mp[0].length;j++) {
					if(map.mp[i][j]>0) {
						Rectangle brick=new Rectangle(j*map.brickWidth+80,i*map.brickHeight+50,map.brickWidth,map.brickHeight);
						Rectangle ball=new Rectangle(ballX,ballY,15,15);
						if(ball.intersects(brick)) {
							map.setBrickValue(0, i, j);
							totalbricks--;
							score+=5;
							
							if(ballX+19 <= brick.x || ballX+1 >=brick.x+brick.width) {
								ballXdir = -ballXdir;
							}
							else {
								ballYdir = -ballYdir;
							}
						}
					}
				}
			}
			
			ballX += ballXdir;
			ballY += ballYdir;
			
			if(ballX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballY < 0) {
				ballYdir = -ballYdir;	
			}
			if(ballX > 694) {
				ballXdir=-ballXdir;
			}
		}
		repaint(); //Repaint our window after certain time
				
		//Repaint works when we press any key then the position changes that why need repaint
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		//Pedal Motion Description
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			
			//When Left Key Presses the action that should be done is described below
			
			if(pedalX <= 3) {
				 pedalX=3;
			}
			else {
				moveLeft();
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			//When Right Key Presses the action that should be done is described below
			
			if(pedalX >=597) {
				pedalX=597;
			}
			else {
				moveRight();
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			
			//Restart and setting actions on Enter
			
			if(!play) {
				play=true;
				ballX=120;
				ballY=350;
				ballXdir=-2;
				ballYdir=-1;
				score=0;
				totalbricks=21;
				map=new MapGenerator(3,7);
				
				repaint();
			}
		}
	}
	public void moveRight() {
		pedalX+=20;
	}
	public void moveLeft() {
		pedalX-=20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
