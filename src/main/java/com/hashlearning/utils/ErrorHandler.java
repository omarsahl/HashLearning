package com.hashlearning.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorHandler {

    private static StackPane container;
    private static Stage stage;

    public static final String DEFAULT_MESSAGE = "Something went wrong...";

    public static void showErrorDialog(String message, String stackTraceString){
        container = new StackPane();
        container.setEffect(new DropShadow(10, Color.BLACK));

        Scene scene = new Scene(container);
        scene.getStylesheets().add(ErrorHandler.class.getResource("/css/dialog_stylesheet.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);

        JFXDialogLayout content = new JFXDialogLayout();

        Label heading = new Label(message);
        heading.getStyleClass().add("jfx-layout-heading");
        content.setHeading(heading);

        Label body = new Label(stackTraceString);
        body.setMaxWidth(400);
        body.getStyleClass().add("jfx-layout-body");
        body.setWrapText(true);
        content.setBody(body);

        JFXButton button = new JFXButton("OK");
        button.getStyleClass().add("dialog-accept");
        button.setButtonType(JFXButton.ButtonType.FLAT);
        button.setOnAction(event -> Platform.exit());
        content.setActions(button);

        JFXDialog dialog = new JFXDialog(container, content, JFXDialog.DialogTransition.CENTER);

        stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        StageInitializer.initializeStage(stage);
        stage.show();
        dialog.show();

    }
}
