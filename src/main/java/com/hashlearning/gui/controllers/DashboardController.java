package com.hashlearning.gui.controllers;

import com.hashlearning.utils.ErrorHandler;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {



    @FXML
    private JFXListView<?> list_view;
    @FXML
    private GridPane dashboardPageContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DashboardManager dashboardManager = new DashboardManager();

        list_view.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("old: " + oldValue + " || new: " + newValue);
                dashboardManager.open(newValue.intValue(), dashboardPageContainer);
            }
        });

        // call DashboardManager.open(0) to open Dashboard after initialization so that the user doesn't have to look at some empty boring page.
        list_view.getSelectionModel().select(0 ); // 0 is Dashboard

    }



}
