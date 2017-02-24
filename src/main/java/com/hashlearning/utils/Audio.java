package com.hashlearning.utils;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by ahmed-ayman on 12/12/16
 */
public class Audio {

    public static void playOnClick() throws IOException {
        Media media = new Media(Audio.class.getResource("/sounds/click.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        }
        }
