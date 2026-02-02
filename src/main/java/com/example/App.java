package com.example;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        app.get("/", ctx -> ctx.result("Hello from Tagger!"));

        app.get("/generatetags", ctx -> {
            String artist = ctx.queryParamOrDefault("artist", "Unknown Artist");
            String song = ctx.queryParamOrDefault("song", "Unknown Song");
            ctx.json(Map.of(
                "message", "Tags generated!",
                "artist", artist,
                "song", song,
                "tags", List.of("tag1", "tag2")
            ));
        });
    }
}
