package com.hashlearning.gui.custom_views;

import com.hashlearning.gui.controllers.CourseWebViewController;
import com.hashlearning.models.Course;
import com.hashlearning.utils.ErrorHandler;
import com.hashlearning.utils.StageNavigator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Omar Sahl on 8/Dec/2016
 */
public class CourseListItem extends ListCell<Course> {

    @FXML
    private GridPane listItemParent;

    @FXML
    private ImageView courseIcon;

    @FXML
    private Label courseName;

    @FXML
    private JFXButton continueBtn;

    @FXML
    private JFXProgressBar progressBar;


    private FXMLLoader loader;

    @Override
    protected void updateItem(Course course, boolean empty) {
        super.updateItem(course, empty);


        if (empty || course == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/fxml/course_list_item.fxml"));
                loader.setController(this);

                try {
                     loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            courseName.setText(String.valueOf(course.getName()));

            URL iconPath = getClass().getResource("/courses_icons/" + course.getName().toLowerCase() + ".png");
            if (iconPath != null) {
                courseIcon.setImage(new Image(iconPath.toString()));
            } else {
                courseIcon.setImage(new Image(getClass().getResource("/courses_icons/default_icon.png").toString()));
            }

            continueBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    openCourseWebView(course);
                }
            });

            setText(null);
            setGraphic(listItemParent);

        }

    }

    private void openCourseWebView(Course course) {
        CourseWebViewController controller = new CourseWebViewController(course);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/course_webview.fxml"));
        loader.setController(controller);

        try {
            Parent root = loader.load();
            StageNavigator.switchStage(root, true).show();
        } catch (IOException e) {
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            e.printStackTrace();
        }
    }

}
