package com.hashlearning.models;


import java.util.ArrayList;

public class Tutorial {

    private String name;
    private ArrayList<TutorialContent> contentList;

    public Tutorial(String name) {
        this.name = name;
        contentList = new ArrayList<>();
    }

    public void addTutorialContent(TutorialContent content){
        contentList.add(content);
    }

    public ArrayList<TutorialContent> getContentList() {
        return contentList;
    }

    public void setContentList(ArrayList<TutorialContent> contentList) {
        this.contentList = contentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
