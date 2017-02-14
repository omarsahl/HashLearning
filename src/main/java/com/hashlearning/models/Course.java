package com.hashlearning.models;


import com.hashlearning.utils.ErrorHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

public class Course {

    private final String name;
    private ArrayList<Tutorial> tutorials;

    public static final String PATH_FIRST_SEGMENT = "/tutorials/";


    public Course(String name) {
        this.name = name;
        tutorials = new ArrayList<>();
    }

    private String getHtmlFilePath() {
        return PATH_FIRST_SEGMENT + getName().toLowerCase() + "/";
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tutorial> getTutorials() {
        return tutorials;
    }

    public void setTutorials(ArrayList<Tutorial> tutorials) {
        this.tutorials = tutorials;
    }


    public void addTutorial(Tutorial tutorial) {
        tutorials.add(tutorial);
    }


    public void loadTutorials(String tutorialsFilePath) {
        try {
            File htmlFile = new File(getClass().getResource(tutorialsFilePath).toURI());
            Document doc = Jsoup.parse(htmlFile, "UTF-8");

            Iterator<Element> iterator = doc.getElementById("ListOfContent").children().iterator();

            while (iterator.hasNext()) {
                Element element = iterator.next();
                Tutorial tutorial = new Tutorial(element.select("a").first().ownText());

                for (Element elementChild : element.select("ul").first().getElementsByClass("tocli")) {
                    String title = elementChild.select("a").first().ownText();
                    String htmlFilePath = getHtmlFilePath() + elementChild.select("a").first().attr("href");
                    System.out.println("title: " + title + "\t htmlFilePath: " + htmlFilePath);
                    TutorialContent newContent = new TutorialContent(title, htmlFilePath);
                    tutorial.addTutorialContent(newContent);
                }

                System.out.println(element.select("ul").first().getElementsByClass("tocli").size());

                addTutorial(tutorial);
            }


        } catch (URISyntaxException | IOException e) {
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Course)) {
            return false;
        }
        return ((Course) obj).getName().equals(this.getName());
    }
}
