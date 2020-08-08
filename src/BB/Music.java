package BB;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Music {
	 Clip clip=null;
	 public void playMusic(String path) {
		 try {
			 File input=new File(path);
			 if(input.exists()) {
				 AudioInputStream audio=AudioSystem.getAudioInputStream(input.getAbsoluteFile());
				 
				 DataLine.Info info=new DataLine.Info(Clip.class, audio.getFormat());
				 clip=(Clip)AudioSystem.getLine(info);
				 clip.open(audio);
				 clip.start();
				 clip.loop(Clip.LOOP_CONTINUOUSLY);
				 
			 }
			 else {
				 System.out.println("Cannot Find Music File");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

}
