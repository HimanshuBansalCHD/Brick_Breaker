package BB;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	
	int[][] mp;
	int brickHeight;
	int brickWidth;
	MapGenerator(int row,int col){
		mp=new int[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				mp[i][j]=1;
			}
		}
		brickWidth=540/col;
		brickHeight=150/row;
	}
	
	public void draw(Graphics2D g) {
		
		for(int i=0;i<mp.length;i++) {
			for(int j=0;j<mp[0].length;j++) {
				if(mp[i][j]>0) {
					g.setColor(Color.black);
					g.fillRect(j*brickWidth + 80, i*brickHeight+50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.white);
					g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
		
	}
	
	public void setBrickValue(int val,int row,int col) {
		mp[row][col]=val;
	}
	
	
	
}
