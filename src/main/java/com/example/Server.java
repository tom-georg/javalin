package com.example;

import com.example.Handlers.BeitragGetListHandler;
import com.example.Handlers.BeitragPostCreateHandler;
import com.example.Handlers.HelloHandler;
import com.example.Modell.Beitrag;
import com.example.Modell.Manager;

import io.javalin.Javalin;

public class Server {

    public Server() {

        Manager manager = Manager.getInstance();
        manager.addBeitrag(new Beitrag("Hallo", "Anna"));


        Javalin app = Javalin.create(config -> {
            config.http.maxRequestSize = (int) (0.5 * 1024 * 1024);

        });
        app.get("/", new HelloHandler());
        app.get("/beitrag/", new HelloHandler());

        app.post("/beitrag/create/", Manager::create);
        app.get("/beitrag/list/", Manager::list);
        app.get("/beitrag/{id}/", Manager::getOne); // Updated route path

        app.start(7070);
    }

}
