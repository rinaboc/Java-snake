package sound;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    private URL[] url = new URL[4];
    private Clip clip;

    public Sound() {
        url[0] = this.getClass().getClassLoader().getResource("snake-bgm.wav");
        url[1] = this.getClass().getClassLoader().getResource("eat-sfx.wav");
        url[2] = this.getClass().getClassLoader().getResource("game-over-sfx.wav");
        url[3] = this.getClass().getClassLoader().getResource("game-start-sfx.wav");

    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){

        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setVolume(float value){
        FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(value);
    }

    public void stop(){
        clip.stop();
    }
}
