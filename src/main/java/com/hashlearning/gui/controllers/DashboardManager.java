package com.hashlearning.gui.controllers;

import com.hashlearning.gui.custom_views.CourseListItem;
import com.hashlearning.models.Course;
import com.hashlearning.utils.DataManager;
import com.hashlearning.utils.ErrorHandler;
import com.hashlearning.utils.SessionManager;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardManager {

    private GridPane container;

    public void open(int index, GridPane container) {
        this.container = container;
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
                openEditor();
            case 4:
                try {
                    SessionManager.signOut((Stage) container.getScene().getWindow());
                } catch (IOException e) {
                    e.printStackTrace();
                    ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
                }
                break;

        }
    }

    private void openEditor() {

        System.out.println("EDITOR");
    }

    private void clearContainer() {
        container.getChildren().clear();
    }

    private void openDashboard() {
        System.out.println("Dashboard selected!");
        clearContainer();
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStylesheets().add(getClass().getResource("/css/jfoenix-components.css").toExternalForm());
        tabPane.setTabMinHeight(60);
        tabPane.setTabMinWidth(200);
        //MY COURSES TAB

        Tab tab1 = new Tab();
        tab1.setText("MY COURSES");
        tab1.setContent(createCoursesListView());

        // tabPane.getTabs().add(tab1);
        Tab tab2 = new Tab();
        tab2.setText("MY ASSIGNMENTS");
        Label assignmentsLabel = new Label("Assignments Page!");
        tab2.setContent(assignmentsLabel);
        assignmentsLabel.setStyle("-fx-font-size: 50px");
        //tabPane.getTabs().add(tab2);
        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.getSelectionModel().select(0);

        container.getChildren().add(tabPane);
    }

    private void openCoursesPage() {
        clearContainer();
        container.getChildren().add(createCoursesListView());
    }

    private void openAssignmentsPage() {
        clearContainer();
    }

    private ListView createCoursesListView() {
        JFXListView<Course> courses = new JFXListView<Course>();
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList(DataManager.students.get(SessionManager.getCurrentStudent()).getEnrolledCourses());
        courses.setItems(courseObservableList);
        courses.setCellFactory(courseListView -> new CourseListItem());
        return courses;
    }
}
