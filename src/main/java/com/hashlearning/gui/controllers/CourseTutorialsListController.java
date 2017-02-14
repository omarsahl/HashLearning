package com.hashlearning.gui.controllers;

import com.hashlearning.gui.custom_views.HashLearningCoursesListItem;
import com.hashlearning.gui.custom_views.TutorialListItem;
import com.hashlearning.models.Course;
import com.hashlearning.models.CourseFactory;
import com.hashlearning.models.Tutorial;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Omar on 15-Feb-17
 */
public class CourseTutorialsListController implements Initializable{

    private Course course;

    @FXML
    private JFXListView<Tutorial> courseTutorialList;

    @FXML
    private Label courseName;

    public CourseTutorialsListController(Course course) {
        this.course = course;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseName.setText(course.getName());
        ObservableList<Tutorial> tutorialObservableList = FXCollections.observableArrayList(course.getTutorials());

        courseTutorialList.setItems(tutorialObservableList);
        courseTutorialList.setCellFactory(new Callback<ListView<Tutorial>, ListCell<Tutorial>>() {
            @Override
            public ListCell<Tutorial> call(ListView<Tutorial> param) {
                return new TutorialListItem();
            }
        });
    }
}
