package com.example.Handlers;

import org.jetbrains.annotations.NotNull;

import com.example.Modell.Beitrag;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class BeitragPostCreateHandler implements Handler{

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        System.out.println("BeitragPostHandler");
        // IF the request is not a POST request, return 405
        if(!ctx.method().equals("POST")){
            ctx.status(405);
            return;
        }
        try{
            Beitrag beitrag = ctx.bodyAsClass(Beitrag.class);
            System.out.println(beitrag);

            ctx.result("BeitragPostHandler");
            // Set statur to 200
            ctx.status(200);
        }
        catch(Exception e){
            System.out.println(e);
            ctx.status(400);
        }

    }

}
