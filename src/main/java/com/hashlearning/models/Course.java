package com.hashlearning.models;


public class Course {

//    Map<String, Tutorial> tutorial;
    private String name;
    private String contentHtmlFile;

    public Course(String name, String contentHtmlFile) {
        this.name = name;
        this.contentHtmlFile = contentHtmlFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentHtmlFile() {
        return contentHtmlFile;
    }

    public void setContentHtmlFile(String contentHtmlFile) {
        this.contentHtmlFile = contentHtmlFile;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Course))
            return false;

        Course course = (Course) obj;
        return name.equals(course.name);
    }
}
