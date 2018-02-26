package com.prituladima.collectionmapsarchexample.arch.entity;

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

    @Override
    public String toString() {
        return "CellDTO{" +
                "time=" + time +
                ", isLoading=" + isLoading +
                '}';
    }
}