package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.TheGuardianIDataEntity;
import it.unipd.dei.eis.data.serialization.JsonDecoder;
import it.unipd.dei.eis.presentation.Context;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TheGuardianDataSource extends DataSource<TheGuardianIDataEntity> {
    private static final String ID = "THEGUARDIAN";
    // private static final String API_KEY = System.getenv("THE_GUARDIAN_API_KEY");
    private static final String API_KEY = "***REMOVED***";
    private static final String BASE_URL = "https://content.guardianapis.com";
    private static final String SEARCH_ENDPOINT = "search";
    private static final String RESPONSE_FORMAT = "json";
    private static final String FIELDS = "bodyText";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final OkHttpClient httpClient = new OkHttpClient();

    public TheGuardianDataSource() {
        super(ID, new JsonDecoder());
    }

    @Override
    public List<TheGuardianIDataEntity> get(Context context) throws Exception {
        assert decoder != null;
        final HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL)).newBuilder()
                .addPathSegment(SEARCH_ENDPOINT)
                .addQueryParameter("api-key", API_KEY)
                .addQueryParameter("format", RESPONSE_FORMAT)
                .addQueryParameter("show-fields", FIELDS);
        if (context.query != null) urlBuilder.addQueryParameter("q", context.query);
        if (context.fromDate != null) urlBuilder.addQueryParameter("from-date", dateFormat.format(context.fromDate));
        if (context.toDate != null) urlBuilder.addQueryParameter("to-date", dateFormat.format(context.toDate));
        urlBuilder.addQueryParameter("page-size", context.countArticles.toString());
        try (Response response = httpClient.newCall(new Request.Builder().url(urlBuilder.build()).build()).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            if (response.body() == null) throw new IOException("Response body is null");
            return Collections.singletonList(decoder.decode(response.body().string(), TheGuardianIDataEntity.class));
        }
    }
}