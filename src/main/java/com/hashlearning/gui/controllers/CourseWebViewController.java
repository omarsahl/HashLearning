package com.hashlearning.gui.controllers;

import com.hashlearning.models.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Omar on 13-Feb-17
 */
public class CourseWebViewController implements Initializable {

    private Course course;

    @FXML
    private WebView courseView;

    @FXML
    private Label courseNameLabel;

    public CourseWebViewController() {
    }

    public CourseWebViewController(Course course) {
        this.course = course;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseNameLabel.setText(course.getName() + " Course");
        WebEngine engine = courseView.getEngine();

        System.out.println("in initialize: " + course.getName() + "\t" + course.getContentHtmlFile());

        if (course.getContentHtmlFile() != null) {
            engine.load(getClass().getResource(course.getContentHtmlFile()).toExternalForm());
        }
        else {
            engine.load(getClass().getResource("/courses_html/no_content.html").toExternalForm());
        }
    }
}
