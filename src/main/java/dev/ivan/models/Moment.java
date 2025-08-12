package dev.ivan.models;

public class Moment {

    private String title;
    private String date;
    private String description;

    public Moment(String title, String date, String description)
    {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

        @Override
    public String toString() {
        return "Título: " + title + "\n" +
               "Fecha: " + date + "\n" +
               "Descripción: " + description + "\n";
    }

}
