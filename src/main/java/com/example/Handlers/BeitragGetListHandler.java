package com.example.Handlers;



import io.javalin.http.Context;
import io.javalin.http.Handler;

public class BeitragGetListHandler implements Handler{

    @Override
    public void handle(Context ctx) throws Exception {
        System.out.println("BeitragGetListHandler");
        ctx.result("Hello World ");


    }

}
