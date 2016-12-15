package com.hashlearning.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ahmed-ayman on 12/12/16.
 */
public class Audio {

    public static void playOnClick(Object object) throws IOException {

        Media media = new Media(Audio.class.getResource("/sounds/click.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
