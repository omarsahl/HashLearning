package com.hashlearning.utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ahmed-ayman on 12/12/16.
 */
public class Audio {

    public static void playOnClick(Object object) throws IOException {
        ClassLoader CLDR = object.getClass().getClassLoader();
        InputStream soundName = CLDR.getResourceAsStream("sounds/click.wav");
        AudioStream audioStream = new AudioStream(soundName);
        AudioPlayer.player.start(audioStream);
    }
}
