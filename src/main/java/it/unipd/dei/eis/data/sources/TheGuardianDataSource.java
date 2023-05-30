package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.TheGuardianDataEntity;
import it.unipd.dei.eis.data.serialization.JsonDecoder;
import it.unipd.dei.eis.presentation.Context;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * TheGuardianDataSource is the data source for The Guardian records.
 * It contains the data source logic.
 */
public class TheGuardianDataSource extends DataSource<TheGuardianDataEntity> {

    /**
     * The ID field is used to identify the data source.
     */
    public static final String ID = "THEGUARDIAN";

    /**
     * The ENV_API_KEY field is used to get the API key from the environment variables.
     */
    private static final String ENV_API_KEY = System.getenv("THE_GUARDIAN_API_KEY");
    // private static final String API_KEY = "***REMOVED***";

    /**
     * The BASE_URL field is used to build the URL of the request.
     */
    private static final String BASE_URL = "https://content.guardianapis.com";

    /**
     * The SEARCH_ENDPOINT field is used to build the URL of the request.
     */
    private static final String SEARCH_ENDPOINT = "search";

    /**
     * The RESPONSE_FORMAT field is used to build the URL of the request.
     */
    private static final String RESPONSE_FORMAT = "json";

    /**
     * The FIELDS field is used to build the URL of the request.
     */
    private static final String FIELDS = "bodyText";

    /**
     * The dateFormat field is used to format the date.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The MAX_PAGE_SIZE field is used to limit the number of articles per page.
     */
    private static final int MAX_PAGE_SIZE = 200;

    /**
     * The httpClient field is used to send the HTTP request.
     */
    private final OkHttpClient httpClient = new OkHttpClient();

    /**
     * The TheGuardianDataSource constructor.
     * It is used to create a new TheGuardianDataSource object.
     */
    public TheGuardianDataSource() {
        super(ID, new JsonDecoder());
    }

    /**
     * The get method is used to get the data from The Guardian API and deserialize it.
     * Pagination is processed in an asynchronous way using multiple threads.
     *
     * @param context the context of the request
     * @return the list of TheGuardianDataEntity objects
     */
    @Override
    public List<TheGuardianDataEntity> get(Context context) {
        String apiKey = context.apiKey != null ? context.apiKey : ENV_API_KEY;
        if (apiKey == null) {
            throw new IllegalArgumentException("TheGuardian API key is missing");
        }
        ArrayList<Future<TheGuardianDataEntity>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors());
        for (int i = 0; i <= context.countArticles / MAX_PAGE_SIZE; i++) {
            int tempIndex = i;
            futures.add(executorService.submit(() -> {
                final HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL))
                        .newBuilder()
                        .addPathSegment(SEARCH_ENDPOINT)
                        .addQueryParameter("api-key", apiKey)
                        .addQueryParameter("format", RESPONSE_FORMAT)
                        .addQueryParameter("show-fields", FIELDS)
                        .addQueryParameter("page-size", String.valueOf(tempIndex == context.countArticles / MAX_PAGE_SIZE ? context.countArticles % MAX_PAGE_SIZE : MAX_PAGE_SIZE))
                        .addQueryParameter("page", String.valueOf(tempIndex + 1));
                if (context.query != null) {
                    urlBuilder.addQueryParameter("q", context.query);
                }
                if (context.fromDate != null) {
                    urlBuilder.addQueryParameter("from-date", DATE_FORMAT.format(context.fromDate));
                }
                if (context.toDate != null) {
                    urlBuilder.addQueryParameter("to-date", DATE_FORMAT.format(context.toDate));
                }
                try (Response response = httpClient.newCall(new Request.Builder().url(urlBuilder.build())
                                .build())
                        .execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException(String.format("Unexpected response code %s", response.code()));
                    }
                    if (response.body() == null) {
                        throw new IOException("Response body is empty");
                    }
                    return Objects.requireNonNull(decoder)
                            .decode(response.body()
                                    .string(), TheGuardianDataEntity.class);
                }
            }));
        }
        executorService.shutdown();
        return futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}