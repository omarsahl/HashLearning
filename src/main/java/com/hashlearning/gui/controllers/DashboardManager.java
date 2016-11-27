package com.hashlearning.gui.controllers;

import com.hashlearning.utils.SessionManager;
import com.jfoenix.controls.JFXTabPane;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardManager {

    public static final int DASHBOARD = 0;
    public static final int COURSES = 1;
    public static final int ASSIGNMENTS = 2;
    public static final int LOGOUT = 3;

    private GridPane container;

    public void open(int index) {
        switch (index) {
            case 0:
                openDashboard();
                break;
            case 1:
                openCoursesPage();
                break;
            case 2:
                openAssignmentsPage();
                break;
            case 3:
                try {
                    SessionManager.signOut((Stage) container.getScene().getWindow());
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    private void openDashboard() {
        System.out.println("Dashboard opened!");

        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStylesheets().add(getClass().getResource("/css/jfoenix-components.css").toExternalForm());
        tabPane.setTabMinHeight(60);
        tabPane.setTabMinWidth(200);

        Tab tab1 = new Tab();
        tab1.setText("COURSES");
        tab1.setContent(new Label("Courses!"));
        tabPane.getTabs().add(tab1);

        Tab tab2 = new Tab();
        tab2.setText("ASSIGNMENTS");
        tab2.setContent(new Label("Assignments!"));
        tabPane.getTabs().add(tab2);
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
    }

    private void openCoursesPage() {

    }

    private void openAssignmentsPage() {

    }

}
