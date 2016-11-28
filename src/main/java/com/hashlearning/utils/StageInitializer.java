package com.hashlearning.utils;


import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageInitializer {


    /**
     * This method initializeStage stage by setting its title to "Hash Learning", and setting its icon to the Hash Learning logo.
     *
     * @param stage the stage to initialize
     */
    public static void initializeStage(Stage stage) {
        stage.setTitle("Hash Learning");
        stage.getIcons().add(new Image(StageInitializer.class.getResource("/HL_hash_logo.png").toString()));
    }

    public static void initializeStage(Stage stage, boolean resizable){
        initializeStage(stage);
        stage.setResizable(resizable);
    }

}
