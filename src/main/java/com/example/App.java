package com.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class App {
    private static final String API_TOKEN = System.getenv("TAGGER_API_TOKEN");

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        app.get("/", ctx -> ctx.result("Hello from Tagger!"));

        app.get("/generatetags", App::handleGenerateTags);
    }

    private static void handleGenerateTags(Context ctx) {
        if (!isAuthorized(ctx)) {
            ctx.status(401).json(Map.of("error", "Unauthorized"));
            return;
        }

        String artist = ctx.queryParam("artist");
        String song = ctx.queryParam("song");
        if (artist == null) artist = "Unknown Artist";
        if (song == null) song = "Unknown Song";

        ctx.json(Map.of(
            "message", "Tags generated!",
            "artist", artist,
            "song", song,
            "tags", List.of("tag1", "tag2")
        ));
    }

    private static boolean isAuthorized(Context ctx) {
        String authHeader = ctx.header("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }
        String token = authHeader.substring(7);
        return API_TOKEN != null && API_TOKEN.equals(token);
    }
}
