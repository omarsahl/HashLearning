package com.hashlearning.gui.controllers;

import com.hashlearning.models.Tutorial;
import com.hashlearning.utils.DatabaseManager;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by ahmed-ayman on 12/18/16.
 */
public class TutorialsController implements Initializable {
    @FXML
    private JFXListView<?> list_view;

    @FXML
    private GridPane dashboardPageContainer;
    private HashMap <String,Tutorial> tutorials ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tutorials= DatabaseManager.javaToutrials;
    Set<String> names = tutorials.keySet();
   Object[] namesArray =  names.toArray();
        System.out.println(tutorials.get("Over View").getContent());
   ObservableList namesList = FXCollections.observableArrayList(Arrays.asList(namesArray));
        list_view.setItems(namesList);
    list_view.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            dashboardPageContainer.getChildren().clear();
            Label label = new Label();
            label.setText(tutorials.get(String.valueOf(namesArray[(int)newValue])).getContent());

            dashboardPageContainer.getChildren().add(label);
        }
    });
    }
}
