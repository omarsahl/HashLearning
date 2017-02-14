package com.hashlearning.gui.custom_views;

import com.hashlearning.gui.controllers.CourseWebViewController;
import com.hashlearning.models.Tutorial;
import com.hashlearning.models.TutorialContent;
import com.hashlearning.utils.ErrorHandler;
import com.hashlearning.utils.StageNavigator;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;

/**
 * Created by Omar on 14-Feb-17
 */
public class TutorialListItem extends ListCell<Tutorial> {

    private FXMLLoader loader;

    @FXML
    private GridPane tutorialListItemParent;

    @FXML
    private Label tutorialName;

    @FXML
    private JFXListView<TutorialContent> tutorialList;

    @Override
    protected void updateItem(Tutorial tutorial, boolean empty) {
        super.updateItem(tutorial, empty);


        if (empty || tutorial == null) {
            setText(null);
            setGraphic(null);

        } else {

            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/fxml/tutorials_list_item_layout.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            tutorialName.setText(String.valueOf(tutorial.getName()));

            ObservableList<TutorialContent> tutorialContents = FXCollections.observableArrayList(tutorial.getContentList());
            tutorialList.setItems(tutorialContents);
            tutorialList.setCellFactory(new Callback<ListView<TutorialContent>, ListCell<TutorialContent>>() {
                @Override
                public ListCell<TutorialContent> call(ListView<TutorialContent> param) {
                    return new ListCell<TutorialContent>() {
                        @Override
                        protected void updateItem(TutorialContent item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null || item.getTitle() == null) {
                                setText(null);
                            } else {
                                setText(item.getTitle());
                            }
                        }
                    };
                }
            });

            tutorialList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    TutorialContent tutorialContent = tutorialList.getSelectionModel().getSelectedItem();
                    System.out.println(tutorialContent.getTitle());
                    openCourseWebView(tutorialContent);
                }
            });

            setText(null);
            setGraphic(tutorialListItemParent);

        }
    }

    private void openCourseWebView(TutorialContent tutorialContent) {
        CourseWebViewController controller = new CourseWebViewController(tutorialContent);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tutorial_webview.fxml"));
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
