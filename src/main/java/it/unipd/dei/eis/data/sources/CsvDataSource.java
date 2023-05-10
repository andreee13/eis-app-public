package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.serialization.CsvDecoder;
import it.unipd.dei.eis.data.serialization.JsonEncoder;

import java.util.List;

public class CsvDataSource extends DataSource {
    private static final String ID = "CSV";

    public CsvDataSource() {
        super(ID, null, null);
    }

    @Override
    public IDataEntity findOne(String query) throws Exception {
        return null;
    }

    @Override
    public List<? extends IDataEntity> findMany(String query) throws Exception {
        return null;
    }

    @Override
    public List<? extends IDataEntity> findAll() throws Exception {
        return null;
    }
}