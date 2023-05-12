package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.serialization.IDecoder;
import org.apache.commons.cli.CommandLine;

import java.util.List;

public abstract class DataSource {
    public final String id;

    final IDecoder decoder;

    protected DataSource(String id, IDecoder decoder) {
        this.id = id;
        this.decoder = decoder;
    }

    public abstract List<? extends IDataEntity> get(CommandLine cmd) throws Exception;
}

