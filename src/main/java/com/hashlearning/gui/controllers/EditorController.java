package com.hashlearning.gui.controllers;

import com.hashlearning.utils.Audio;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ahmed-ayman on 12/14/16.
 */
public class EditorController implements Initializable {


    @FXML
    private JFXButton HowTo;

    @FXML
    void HowToBtn(MouseEvent event) throws IOException {
        Audio.playOnClick(this);
        JOptionPane.showMessageDialog(null,"You have to open the Cmd ...");
    }
        @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
