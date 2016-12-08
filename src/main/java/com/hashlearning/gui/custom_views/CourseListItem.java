package com.hashlearning.gui.custom_views;

import com.hashlearning.models.Course;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by Omar Sahl on 8/Dec/2016
 */
public class CourseListItem extends ListCell<Course> {

    @FXML
    private ImageView courseIcon;

    @FXML
    private Label courseName;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private JFXButton continueBtn;

    @FXML
    private HBox listItemParent;


    private FXMLLoader loader;

    @Override
    protected void updateItem(Course course, boolean empty) {
        super.updateItem(course, empty);


        if (empty || course == null) {
            System.out.println("empty >> " + empty);
            System.out.println((course == null) ? "course is null = true" : "course is null = false" );
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
            courseIcon.setImage(new Image(getClass().getResource("/courses_icons/" + course.getName().toLowerCase() + ".png").toString()));

            setText(null);
            setGraphic(listItemParent);


        }

    }

}
