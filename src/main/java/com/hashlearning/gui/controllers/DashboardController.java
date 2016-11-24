package com.hashlearning.gui.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.F;
import static javafx.scene.paint.Color.WHITE;

public class DashboardController implements Initializable {

    @FXML
    private JFXListView<?> list_view;

    @FXML
    private Label dashboardBtn;

    @FXML
    private Label coursesBtn;

    @FXML
    private GridPane dashboardPageContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // call openDashboard after initialization so that the user doesn't have to look at some empty boring page.
        openDashboard();
        dashboardBtn.setOnMouseClicked(event -> openDashboard());
    }

    public void openDashboard(){
        System.out.println("Dashboard opened!");

        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStylesheets().add(getClass().getResource("/css/jfoenix-components.css").toExternalForm());
        tabPane.setTabMinHeight(60);
        tabPane.setTabMinWidth(200);
        Tab tab1 = new Tab();
        tab1.setText("Courses");
        tab1.setContent(new Label("Courses!"));
        tabPane.getTabs().add(tab1);

        Tab tab2 = new Tab();
        tab2.setText("Assignments");
        tab2.setContent(new Label("Assignments!"));
        tabPane.getTabs().add(tab2);
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(1);

        dashboardPageContainer.getChildren().add(tabPane);
    }

    public void openCoursesPage(){

    }

}
