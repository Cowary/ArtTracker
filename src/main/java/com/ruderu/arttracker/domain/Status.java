package com.ruderu.arttracker.domain;

public enum Status {
    READY("Ready to start"),
    WATCHING("Watching"),
    READING("Reading"),
    PLAYING("Playing"),
    LISTENING("Listening"),
    DROPPED("Dropped"),
    FINISHED("Finished");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
