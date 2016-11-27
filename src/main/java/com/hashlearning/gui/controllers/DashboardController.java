package com.hashlearning.gui.controllers;

import com.hashlearning.utils.ErrorHandler;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXListView<?> list_view;
    @FXML
    private GridPane dashboardPageContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // call DashboardManager.open(DashboardManager.DASHBOARD) to open Dashboard after initialization so that the user doesn't have to look at some empty boring page.
        list_view.getSelectionModel().select(0);

    }



}
