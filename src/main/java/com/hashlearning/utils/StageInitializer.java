package com.hashlearning.utils;


import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageInitializer {


    /**
     * This method initialize stage by setting its title to "Hash Learning", and set its icon to the Hash Learning logo.
     *
     * @param stage the stage to initialze
     */
    public static void initialize(Stage stage) {
        stage.setTitle("Hash Learning");
        stage.getIcons().add(new Image(StageInitializer.class.getResource("/HL_hash_logo.png").toString()));
    }

}
