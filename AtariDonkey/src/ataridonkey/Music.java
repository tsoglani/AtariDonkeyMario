/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ataridonkey;
import java.applet.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author gaitanesnikos
 */
public class Music extends Applet {
 Clip clip; 
    
    
  public Music(){
        try {
            File file = new File("Mario.au"); 
            AudioClip sound = Applet.newAudioClip(file.toURL()); 
            sound.play( );
            
//            
          

//            if(songName!=null){
//            this.songName=songName;
              
////              AudioInputStream audio ;
////                  clip =  AudioSystem.getClip();
////            audio = AudioSystem.getAudioInputStream(new File("Mario.au"));
////                   
////                    clip.open(audio);
////                    clip.start();

//////        } catch (UnsupportedAudioFileException ex) {
//////          ex.printStackTrace();
//////        } catch (Exception ex) {
//////           ex.printStackTrace();
//////        }
        } catch (Exception ex) {
          //  ex.printStackTrace();
            
        }
           
  
  }

   
    
    
    
}
