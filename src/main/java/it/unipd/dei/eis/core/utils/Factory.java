package it.unipd.dei.eis.core.utils;

import org.apache.commons.cli.CommandLine;

public abstract class Factory<T> {
    protected final CommandLine cmd;

    public Factory(CommandLine cmd) {
        this.cmd = cmd;
    }

    public abstract T create(String name) throws IllegalArgumentException;
}
