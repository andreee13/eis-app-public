package it.unipd.dei.eis.data.entities;

public class ArticleTermsDataEntity implements IDataEntity {
    final public String title;
    final public String body;

    public ArticleTermsDataEntity(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("%s %s", title, body);
    }
}
