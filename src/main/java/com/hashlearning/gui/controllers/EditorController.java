package com.hashlearning.gui.controllers;

import com.Ostermiller.Syntax.HighlightedDocument;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by ahmed-ayman on 12/14/16
 */
public class EditorController implements Initializable {

    @FXML
    private JFXComboBox<Label> langsComboBox;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private StackPane editorContainer;

    private ObservableList<Label> languages;

    private HighlightedDocument document;

    private JTextPane textPane;

    private HashMap<String, Object> languagesKeys;

    @FXML
    void saveButtonOnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initEditor();
        initLanguagesComboBox();
        SwingNode textPaneSwingNode = new SwingNode();
        createAndSetSwingContent(textPaneSwingNode);
        editorContainer.getChildren().add(textPaneSwingNode);
        textPane.setEditable(true);
    }


    private void initEditor() {
        document = new HighlightedDocument();
        textPane = new JTextPane(document);
        initDocument();

        languagesKeys = new HashMap<String, Object>();
        languagesKeys.put("Java", HighlightedDocument.JAVA_STYLE);
        languagesKeys.put("C/C++", HighlightedDocument.C_STYLE);
        languagesKeys.put("HTML (Simple)", HighlightedDocument.HTML_STYLE);
        languagesKeys.put("HTML (Complex)", HighlightedDocument.HTML_KEY_STYLE);
        languagesKeys.put("LaTeX", HighlightedDocument.LATEX_STYLE);
        languagesKeys.put("SQL", HighlightedDocument.SQL_STYLE);
        languagesKeys.put("Java Properties", HighlightedDocument.PROPERTIES_STYLE);
        languagesKeys.put("Plain", HighlightedDocument.PLAIN_STYLE);
        languagesKeys.put("Grayed Out", HighlightedDocument.GRAYED_OUT_STYLE);
    }

    private void initLanguagesComboBox() {
        languages = FXCollections.observableArrayList(
                new Label("Java"),
                new Label("C/C++"),
                new Label("HTML (Simple)"),
                new Label("HTML (Complex)"),
                new Label("LaTeX"),
                new Label("SQL"),
                new Label("Java Properties"),
                new Label("Plain"),
                new Label("Grayed out"));

        langsComboBox.setPromptText("Select Your Programming Language");
        langsComboBox.getStylesheets().add(getClass().getResource("/css/combo_box_stylesheet.css").toExternalForm());
        langsComboBox.setItems(languages);
        langsComboBox.setEditable(false);
        langsComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {
            @Override
            public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
                System.out.println("newValue: " + newValue.getText());
                document.setHighlightStyle(languagesKeys.get(newValue.getText()));
            }
        });
    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(textPane);
            }
        });
    }

    private void initDocument() {
        String initString = (
                "/**\n" +
                        " * Simple common test program.\n" +
                        " */\n" +
                        "public class HelloWorld {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // Display the greeting.\n" +
                        "        System.out.println(\"Hello World!\");\n" +
                        "    }\n" +
                        "}\n"
        );

        textPane.setText(initString);
    }

}
