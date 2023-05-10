package it.unipd.dei.eis.data.sources;


import it.unipd.dei.eis.data.serialization.JsonDecoder;
import it.unipd.dei.eis.data.serialization.JsonEncoder;
import it.unipd.dei.eis.data.entities.TheGuardianIDataEntity;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TheGuardianDataSource extends DataSource {
    private static final String ID = "THEGUARDIAN";
    private static final String API_KEY = System.getenv("THE_GUARDIAN_API_KEY");
    private static final String BASE_URL = "https://content.guardianapis.com";
    private static final String SEARCH_ENDPOINT = "search";
    private static final String RESPONSE_FORMAT = "json";
    private static final String FIELDS = "bodyText";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final OkHttpClient httpClient = new OkHttpClient();
    private String section;
    private String tag;
    private Date fromDate;
    private Date toDate;

    public TheGuardianDataSource() {
        super(ID, new JsonEncoder(), new JsonDecoder());
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public TheGuardianIDataEntity findOne(String query) throws Exception {
        final HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL)).newBuilder()
                .addPathSegment(SEARCH_ENDPOINT)
                .addQueryParameter("api-key", API_KEY)
                .addQueryParameter("format", RESPONSE_FORMAT)
                .addQueryParameter("show-fields", FIELDS);
        if (query != null) urlBuilder.addQueryParameter("q", query);
        if (section != null) urlBuilder.addQueryParameter("section", section);
        if (tag != null) urlBuilder.addQueryParameter("tag", tag);
        if (fromDate != null) urlBuilder.addQueryParameter("from-date", dateFormat.format(fromDate));
        if (toDate != null) urlBuilder.addQueryParameter("to-date", dateFormat.format(toDate));
        try (Response response = httpClient.newCall(new Request.Builder().url(urlBuilder.build()).build()).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            if (response.body() == null) throw new IOException("Response body is null");
            return decoder.decode(response.body().string(), TheGuardianIDataEntity.class);
        }
    }

    @Override
    public List<TheGuardianIDataEntity> findMany(String query) throws Exception {
        return Collections.singletonList(findOne(query));
    }

    @Override
    public List<TheGuardianIDataEntity> findAll() throws Exception {
        return Collections.singletonList(findOne(null));
    }
}