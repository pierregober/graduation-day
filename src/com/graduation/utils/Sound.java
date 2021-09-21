package com.graduation.utils;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.awt.event.KeyEvent;

public class Sound {
    private static float volume;


    public Sound(){
        volume = 1.0f;
    }
    public Sound(float volume){
        this.volume = volume;
    }
    private final BlockingQueue<URL> queue = new ArrayBlockingQueue<URL>(1);
    public void playSoundClip(String audioFilePath) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        File audioFile = new File(audioFilePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        volumeControl(audioClip);
        muteControl(audioClip);
        audioClip.start();

        LineListener listener = new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() != LineEvent.Type.STOP) {
                    audioClip.close();
                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


    }
    public void volumeControl(Clip audioClip){
        FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = volumeControl.getMaximum() - volumeControl.getMinimum();
        float gain = (range * Global.volume) + volumeControl.getMinimum();
        volumeControl.setValue(gain);
    }
    public void muteControl(Clip audioClip){
        BooleanControl muteControl = (BooleanControl) audioClip.getControl(BooleanControl.Type.MUTE);
        muteControl.setValue(Global.mute);
    }



}
