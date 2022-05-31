package com.ruderu.arttracker.domain;

public enum Status {
    READY("Ready to start"),
    IN_PROGRESS("In progress"),
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
