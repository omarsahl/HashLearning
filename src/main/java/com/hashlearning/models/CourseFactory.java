package com.hashlearning.models;




public class CourseFactory {
    private static final String JAVA_TUTORIALS_FOLDER_PATH = "/courses_html/java_toc.html";
    private static final String C_TUTORIALS_FOLDER_PATH = "/courses_html/java_toc.html";

    public enum CourseName {
        JAVA , C
    }


    public Course buildCourse(CourseName courseName){
        switch (courseName){
            case JAVA:
                return buildJavaCourse();
            case C:
                return buildCCourse();
            default:
                return null;
        }
    }

    private Course buildCCourse() {
        Course cCourse = new Course("C");
        cCourse.loadTutorials(C_TUTORIALS_FOLDER_PATH);
        return cCourse;
    }

    private Course buildJavaCourse() {
        Course javaCourse = new Course("Java");
        javaCourse.loadTutorials(JAVA_TUTORIALS_FOLDER_PATH);
        return javaCourse;
    }

}
