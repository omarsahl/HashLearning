package com.hashlearning.gui.controllers;

import com.hashlearning.models.TutorialContent;
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

    private TutorialContent tutorialContent;

    @FXML
    private WebView courseView;

    @FXML
    private Label courseNameLabel;

    public CourseWebViewController() {
    }

    public CourseWebViewController(TutorialContent tutorialContent) {
        this.tutorialContent = tutorialContent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseNameLabel.setText(tutorialContent.getTitle());
        WebEngine engine = courseView.getEngine();

        System.out.println("in initialize: " + tutorialContent.getTitle() + "\t" + tutorialContent.getHtmlFilePath());

        if (tutorialContent.getHtmlFilePath() != null) {
            engine.load(getClass().getResource(tutorialContent.getHtmlFilePath()).toExternalForm());
        } else {
            engine.load(getClass().getResource("/courses_html/no_content.html").toExternalForm());
        }
    }
}
