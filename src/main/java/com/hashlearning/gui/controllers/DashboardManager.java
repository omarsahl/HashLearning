package com.hashlearning.gui.controllers;

import com.hashlearning.gui.custom_views.CourseListItem;
import com.hashlearning.gui.custom_views.HashLearningCoursesListItem;
import com.hashlearning.models.Course;
import com.hashlearning.utils.ErrorHandler;
import com.hashlearning.utils.SessionManager;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class DashboardManager {

    private GridPane container;

    private static JFXListView<Course> myCourses;
    private static JFXListView<Course> hashLearningCourses;

    public void open(int index, GridPane container) throws IOException {
        this.container = container;
        switch (index) {
            case 0:
                initMyCoursesList();
                openDashboard();
                break;
            case 1:
                initCoursesList();
                openCoursesPage();
                break;
            case 2:
                openEditor();
                break;
            case 3:
                try {
                    SessionManager.signOut((Stage) container.getScene().getWindow());
                } catch (IOException e) {
                    e.printStackTrace();
                    ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
                }
                break;

        }
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
        tab1.setContent(myCourses);
        //MY ASSIGNMENTS TAB
        Tab tab2 = new Tab();
        tab2.setText("MY ASSIGNMENTS");
        Label assignmentsLabel = new Label("Assignments Page!");
        tab2.setContent(assignmentsLabel);
        assignmentsLabel.setStyle("-fx-font-size: 50px");
        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.getSelectionModel().select(0);
        container.getChildren().add(tabPane);
    }

    private void openCoursesPage() {
        clearContainer();
//        Button javaBtn = new Button("Enroll in Java");
//        javaBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Enrolling " + SessionManager.getCurrentUser().getUsername() + " in Java");
//                DatabaseManager.enrollInCourse(new Course("Java"), SessionManager.getCurrentUser());
//            }
//        });
//
//        Button cppBtn = new Button("Enroll in c++");
//        cppBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Enrolling " + SessionManager.getCurrentUser().getUsername() + " in C++");
//                DatabaseManager.enrollInCourse(new Course("C++"), SessionManager.getCurrentUser());
//            }
//        });
        container.getChildren().add(hashLearningCourses);
    }


    private void initMyCoursesList() {
        System.out.println("Initializing MyCoursesList...");
        myCourses = new JFXListView<Course>();
        myCourses.getStylesheets().add(getClass().getResource("/css/courses_list_stylesheet.css").toExternalForm());
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList(SessionManager.getCurrentStudent().getEnrolledCourses());

        System.out.println("courseObservableList");
        for (Course course : courseObservableList) {
            System.out.println(course.getName());
        }

        System.out.println("getEnrolledCourses");
        for (Course course : SessionManager.getCurrentStudent().getEnrolledCourses()) {
            System.out.println(course.getName());
        }
        myCourses.setItems(courseObservableList);
        myCourses.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> courseListView) {
                return new CourseListItem();
            }
        });
    }


    private void initCoursesList() {
        System.out.println("Initializing CoursesList...");
        hashLearningCourses = new JFXListView<Course>();
        hashLearningCourses.getStylesheets().add(getClass().getResource("/css/courses_list_stylesheet.css").toExternalForm());

        ObservableList<Course> courseObservableList = FXCollections.observableArrayList(
                new Course("Java", null),
                new Course("C++", "/courses_html/c++_html.html")
        );

        System.out.println("courseObservableList");
        for (Course course : courseObservableList) {
            System.out.println(course.getName());
        }

        hashLearningCourses.setItems(courseObservableList);
        hashLearningCourses.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> courseListView) {
                return new HashLearningCoursesListItem();
            }
        });
    }

    private void openEditor() throws IOException {
        clearContainer();
        Parent editor = FXMLLoader.load(getClass().getResource("/fxml/editor.fxml"));
        container.getChildren().add(editor);

    }

}
