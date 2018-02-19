package com.prituladima.collectionmapsarchexample.arch.dto;

public final class CellDTO {

    private long time;
    private boolean isLoading;

    public CellDTO(long time, boolean isLoading) {
        this.time = time;
        this.isLoading = isLoading;
    }

    public long getTime() {
        return time;
    }

    public boolean isLoading() {
        return isLoading;
    }
}