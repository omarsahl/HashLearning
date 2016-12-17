package com.hashlearning.gui.controllers;

import com.hashlearning.utils.SessionManager;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label mail;
    @FXML
    private JFXListView<?> list_view;
    @FXML
    private GridPane dashboardPageContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(SessionManager.getCurrentUser().getUsername());
        mail.setText(SessionManager.getCurrentUser().getEmail());

        DashboardManager dashboardManager = new DashboardManager();

        list_view.getStylesheets().add(getClass().getResource("/css/listview_stylesheet.css").toExternalForm());

        list_view.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
// TODO adjusting the Audio..
//      try {
//                    Audio.playOnClick(this);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                try {
                    dashboardManager.open(newValue.intValue(), dashboardPageContainer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // call DashboardManager.open(0) to open Dashboard after initialization so that the user doesn't have to look at some empty boring page.
        list_view.getSelectionModel().select(0); // 0 is Dashboard

    }




}
