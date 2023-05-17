package it.unipd.dei.eis.data.entities;

import java.util.Date;

public class JsonDataEntity implements IDataEntity {
    final public String id;
    final public String title;
    final public String body;
    final public String url;
    final public Date date;
    final public String source;

    public JsonDataEntity(String id, String title, String body, String url, Date date, String source) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.date = date;
        this.source = source;
    }
}

