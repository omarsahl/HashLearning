package com.hashlearning.models;

/**
 * Created by Omar on 14-Feb-17
 */
public class TutorialContent {

    private String title;
    private String htmlFilePath;

    public TutorialContent(String title, String htmlFilePath) {
        this.title = title;
        this.htmlFilePath = htmlFilePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public void setHtmlFilePath(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }
}
