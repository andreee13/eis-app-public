package it.unipd.dei.eis.presentation;

public abstract class Argument {
    public final String key;
    public final String value;

    public Argument(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
