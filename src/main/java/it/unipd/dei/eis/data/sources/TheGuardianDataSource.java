package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.TheGuardianIDataEntity;
import it.unipd.dei.eis.data.serialization.JsonDecoder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TheGuardianDataSource extends DataSource {
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
    public List<TheGuardianIDataEntity> get(CommandLine cmd) throws Exception {
        final HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL)).newBuilder()
                .addPathSegment(SEARCH_ENDPOINT)
                .addQueryParameter("api-key", API_KEY)
                .addQueryParameter("format", RESPONSE_FORMAT)
                .addQueryParameter("show-fields", FIELDS);
        if (cmd.hasOption("query")) urlBuilder.addQueryParameter("q", cmd.getOptionValue("query"));
        if (cmd.hasOption("tag")) urlBuilder.addQueryParameter("tag", cmd.getOptionValue("tag"));
        if (cmd.hasOption("section")) urlBuilder.addQueryParameter("section", cmd.getOptionValue("section"));
        if (cmd.hasOption("from-date"))
            urlBuilder.addQueryParameter("from-date", dateFormat.format(cmd.getOptionValue("from-date")));
        if (cmd.hasOption("to-date"))
            urlBuilder.addQueryParameter("to-date", dateFormat.format(cmd.getOptionValue("to-date")));
        if (cmd.hasOption("count")) urlBuilder.addQueryParameter("page-size", cmd.getOptionValue("count"));
        try (Response response = httpClient.newCall(new Request.Builder().url(urlBuilder.build()).build()).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            if (response.body() == null) throw new IOException("Response body is null");
            return Collections.singletonList(decoder.decode(response.body().string(), TheGuardianIDataEntity.class));
        }
    }
}