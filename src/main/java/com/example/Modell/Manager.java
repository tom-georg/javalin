package com.example.Modell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.javalin.http.Context;

public class Manager {

    private LinkedList<Beitrag> beitraege = new LinkedList<>();
    private LinkedList<Beitrag> beispielBeitraege = new LinkedList<>();
    private static Manager instance = null;

    private Manager() {
        init();
        cleanUpList();
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public void addBeitrag(Beitrag beitrag) {
        beitraege.add(beitrag);
    }

    public List<Beitrag> getBeitraege() {
        return beitraege;
    }

    public static void create(Context ctx) {
        Manager manager = Manager.getInstance();
        manager.cleanUpList();
        Beitrag beitrag = ctx.bodyAsClass(Beitrag.class);
        Manager.getInstance().addBeitrag(beitrag);
        ctx.status(200);
    }

    public static void list(Context ctx) {
        ctx.json(Manager.getInstance().getBeitraege());
    }

    public static void getOne(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        for (Beitrag beitrag : Manager.getInstance().getBeitraege()) {
            if (beitrag.getId() == id) {
                ctx.json(beitrag);
                return;
            }
        }
        ctx.status(404);
    }

    public static void delete(Context ctx) {
        Manager manager = Manager.getInstance();
        manager.cleanUpList();
        int id = Integer.parseInt(ctx.pathParam("id"));
        for (Beitrag beitrag : manager.getBeitraege()) {
            if (beitrag.getId() == id) {
                Manager.getInstance().getBeitraege().remove(beitrag);
                ctx.status(200);
                return;
            }
        }
        ctx.status(404);
    }

    public static void update(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        for (Beitrag beitrag : Manager.getInstance().getBeitraege()) {
            if (beitrag.getId() == id) {
                Beitrag newBeitrag = ctx.bodyAsClass(Beitrag.class);
                beitrag.setText(newBeitrag.getText());
                beitrag.setAbsender(newBeitrag.getAbsender());
                ctx.status(200);
                return;
            }
        }
        ctx.status(404);
    }

    public void cleanUpList(){
        while(beitraege.size() > 300){
            beitraege.remove(0);
        }
        deleteBeitraegeOlderThanMinutes(45);
        while(beitraege.size() < 25){
            Beitrag b = beispielBeitraege.remove(0);
            beitraege.add(b);
            beispielBeitraege.add(b);
        }
    }

    private  void deleteBeitraegeOlderThanMinutes(int minutes){
        long currentTime = System.currentTimeMillis();
        for (Beitrag beitrag : beitraege) {
            if(currentTime - beitrag.getTimestamp().getTime() > minutes * 60 * 1000){
                beitraege.remove(beitrag);
            }
        }
    }




    public void init() {
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Streaming ist das Beste! Ich kann nicht genug bekommen! 🍿"));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Ich vermisse die guten alten TV-Tage. Da war Fernsehen noch ein Ereignis!"));
        beispielBeitraege.add(new Beitrag("TechGeek", "Mit Streaming kann man jederzeit und überall schauen. So praktisch!"));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Genau! Wer will schon Werbung sehen? Bei Streaming gibt's keine Unterbrechungen!"));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Aber was ist mit Live-TV? Reality-Shows sind nicht dasselbe, wenn sie nicht live sind."));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Streaming hat nie die neuesten Filme. Ich gehe lieber ins Kino."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Kinos sind doch überteuert. Streaming ist viel günstiger."));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Es geht nicht nur ums Geld. Es geht um die Erfahrung!"));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Erfahrung? Ich will einfach nur in Ruhe meine Serien bingen. 😎"));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Manchmal ist es lustig, die Reaktionen anderer auf Live-TV in Echtzeit zu sehen."));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Und was ist mit der Kino-Atmosphäre? Das kann Streaming nicht ersetzen!"));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Kino? Wer hat heutzutage Zeit für sowas? Streaming ist sofort verfügbar."));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Es gibt etwas Magisches am Samstagabend-TV. Das hat Streaming nicht."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Magie? Ich nehme lieber die Vielfalt und Bequemlichkeit von Streaming-Diensten."));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Ich vermisse das Rätseln darüber, was als Nächstes im TV kommt."));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Unvorhersehbarkeit? Ich habe meine Serien lieber geplant und jederzeit abrufbar."));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Aber die Bildqualität im Kino ist unübertroffen!"));
        beispielBeitraege.add(new Beitrag("TechGeek", "Bildqualität? Moderne Streaming-Dienste bieten 4K, HDR und mehr."));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "All diese Technologien... Früher war alles einfacher und irgendwie echter."));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Echter? Ich will nicht echt, ich will Unterhaltung und Komfort!"));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "TV bringt die Familie zusammen. Streaming ist so... isolierend."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Isolierend? Nicht wirklich. Ich habe Streaming-Partys mit Freunden!"));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Aber die TV-Programmierung zu Weihnachten? Das ist Tradition!"));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Weihnachten? Da schaue ich die Weihnachtsspecials meiner Lieblingsserien auf Abruf."));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Manche Filme muss man auf einer großen Leinwand sehen, um sie wirklich zu schätzen."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Große Leinwand? Ein guter Fernseher und ein gemütliches Sofa reichen mir."));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Live-TV ist unvorhersehbar, das ist der Reiz!"));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Ja, und die TV-Community! Das Live-Tweeting bei großen Events!"));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Community? Ich habe Online-Foren und Streaming-Chaträume."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Und keine lästigen Werbeunterbrechungen beim Streaming!"));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Ehrlich gesagt, wer nur streamt, verpasst das echte Filmerlebnis."));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Das 'echte Filmerlebnis'? Bitte, sitz nicht so auf deinem hohen Ross!"));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Manche Leute schätzen einfach keine Klassiker mehr. Traurig."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Klassiker? Die meisten sind doch überbewertet. Gebt mir lieber die neuesten Serien."));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Ihr seid alle so fixiert auf eure Bildschirme. Was ist mit der realen Welt?"));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Reale Welt? Wenn ich Realität will, öffne ich das Fenster!"));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Scheinbar hat nicht jeder den Sinn für gute Kinoqualität."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Gute Qualität? Ich lache mich tot. Manche Kinofilme sind totaler Schrott!"));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Es geht um mehr als nur den Inhalt. Es geht um das gemeinsame Erlebnis."));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Gemeinsames Erlebnis? Ich brauche keine anderen Menschen, um mich zu unterhalten."));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Manchmal denke ich, ihr habt alle das Leben außerhalb eurer Streaming-Blase vergessen."));
        beispielBeitraege.add(new Beitrag("TechGeek", "Leben außerhalb der Streaming-Blase? Klingt langweilig."));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Ihr versteht einfach nicht die Bedeutung von Tradition und Kultur."));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Tradition und Kultur? Ich lebe im Hier und Jetzt, nicht in der Vergangenheit!"));
        beispielBeitraege.add(new Beitrag("FilmBuff", "Ach, was weißt du schon über Kultur, wenn du nur Serien bingest?"));
        beispielBeitraege.add(new Beitrag("TechGeek", "Mehr als du denkst. Serien sind das neue Kino!"));
        beispielBeitraege.add(new Beitrag("RealityShowFan", "Könnt ihr nicht alle sehen, dass Fernsehen in jeder Form etwas Gutes hat?"));
        beispielBeitraege.add(new Beitrag("NostalgieFan", "Einige von uns bevorzugen eben Qualitätsinhalte über Quantität."));
        beispielBeitraege.add(new Beitrag("SerienJunkie", "Qualität? Als ob TV jemals Qualität hatte. Es ist nur eine Flut von Werbungen!"));
        beispielBeitraege.add(new Beitrag("TechGeek", "Genau, und Streaming gibt uns die Freiheit zu wählen, was und wann wir schauen."));


    }


}
