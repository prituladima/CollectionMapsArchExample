package com.prituladima.collectionmapsarchexample.entities;

public final class Cell {

    private final long time;
    private final boolean isLoading;

    public Cell(long time, boolean isLoading) {
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
        return "Cell{" +
                "time=" + time +
                ", isLoading=" + isLoading +
                '}';
    }
}
