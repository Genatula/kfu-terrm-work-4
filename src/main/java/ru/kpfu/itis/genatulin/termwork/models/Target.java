package ru.kpfu.itis.genatulin.termwork.models;

public enum Target {
    FRIENDSHIP ("Friendship"),
    PARTNERSHIP ("Partnership");

    private final String value;
    Target(String value) {
        this.value = value;
    }

    public String getValue() {
        return value.toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
