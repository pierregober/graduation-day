package com.graduation.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Sound {

    private final BlockingQueue<URL> queue = new ArrayBlockingQueue<URL>(1);
    public void playSoundClip(String audioFilePath) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        File audioFile = new File(audioFilePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        audioClip.start();
//        while (audioClip.getMicrosecondLength() != audioClip.getMicrosecondPosition()){
//        }
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

}
