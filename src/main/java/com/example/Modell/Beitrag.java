package com.example.Modell;

import java.util.Date;


public class Beitrag {

    private long id;
    private static long idCounter = 0;
    private String text;
    private String absender;
    private Date timestamp;

    public Beitrag(String absender,String text) {

        this.text = text;
        this.absender = absender;
        this.timestamp = new Date();

        id = idCounter++;
        if(idCounter==Long.MAX_VALUE){
            idCounter = 0;
        }

    }

    public Beitrag() {
        this.timestamp = new Date();
        id = idCounter++;
        if(idCounter == Long.MAX_VALUE){
            idCounter = 0;
        }
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAbsender() {
        return absender;
    }

    public void setAbsender(String absender) {
        this.absender = absender;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public String toString() {
        return "Beitrag{" +
                "id='" + id + '\'' +
                "text='" + text + '\'' +
                ", absender='" + absender + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
