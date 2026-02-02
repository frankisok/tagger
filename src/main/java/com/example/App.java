package com.example;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        app.get("/", ctx -> ctx.result("Hello from Tagger!"));

        app.get("/generatetags", ctx -> {
            String artist = ctx.queryParam("artist");
            String song = ctx.queryParam("song");
            // TODO: Implement tag generation logic
            ctx.json(Map.of("artist", artist, "song", song, "tags", List.of("tag1", "tag2")));
        });
    }
}
