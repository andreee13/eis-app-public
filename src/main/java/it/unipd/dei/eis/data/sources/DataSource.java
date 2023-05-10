package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.serialization.IDecoder;
import it.unipd.dei.eis.data.serialization.IEncoder;

import java.util.List;

public abstract class DataSource {
    public final String id;

    final IEncoder encoder;
    final IDecoder decoder;

    protected DataSource(String id, IEncoder encoder, IDecoder decoder) {
        this.id = id;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public abstract IDataEntity findOne(String query) throws Exception;

    public abstract List<? extends IDataEntity> findMany(String query) throws Exception;

    public abstract List<? extends IDataEntity> findAll() throws Exception;
}
