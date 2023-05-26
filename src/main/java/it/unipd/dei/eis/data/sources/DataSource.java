package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.serialization.IDecoder;
import it.unipd.dei.eis.data.serialization.IEncoder;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

public abstract class DataSource<T extends IDataEntity> {
    public final String id;

    protected final IDecoder decoder;
    protected final IEncoder encoder;

    DataSource(String id, IDecoder decoder, IEncoder encoder) {
        this.id = id;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    DataSource(String id, IDecoder decoder) {
        this.id = id;
        this.decoder = decoder;
        this.encoder = null;
    }

    DataSource(String id, IEncoder encoder) {
        this.id = id;
        this.decoder = null;
        this.encoder = encoder;
    }

    DataSource(String id) {
        this.id = id;
        this.decoder = null;
        this.encoder = null;
    }

    public List<T> get(Context context) throws Exception {
        throw new UnsupportedOperationException();
    }

    public void set(Context context, List<T> data) throws Exception {
        throw new UnsupportedOperationException();
    }
}

