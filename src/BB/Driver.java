package BB;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		
		JFrame frame=new JFrame();
		GamePlay gamePlay=new GamePlay();
		frame.setBounds(10,10,700,600);
		frame.setTitle("Brick Breaker 1.0");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(gamePlay);
		String path="/home/jishant/eclipse-workspace/Brick_Breaker/src/GameMusic_.wav";
		Music music=new Music();
		music.playMusic(path);
	}

}
