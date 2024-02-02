package com.example.Handlers;


import io.javalin.http.Context;
import io.javalin.http.Handler;

public class HelloHandler implements Handler{

    private int counter;

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.result("Hello World "+counter);

        counter++;
    }

}
