package com.ruderu.arttracker.domain;

public enum Status {
    PLANNED("Planned"),
    ACTIVE("Active"),
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
