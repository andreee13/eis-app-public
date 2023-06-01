package it.unipd.dei.eis.data.sources;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;
import it.unipd.dei.eis.core.utils.SynchronizedFrequencyCounter;
import it.unipd.dei.eis.data.entities.TermsDataEntity;
import it.unipd.dei.eis.data.serialization.TxtEncoder;
import it.unipd.dei.eis.core.common.Context;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TermsDataSource is the data source for the terms.
 * It contains the data structure of the terms.
 */
public class TermsDataSource extends DataSource<TermsDataEntity> {

    /**
     * The ID of the data source.
     */
    public static final String ID = "TERMS";
    /**
     * The PATTERN is used to check if a word is a punctuation or other special symbols.
     */
    private static final Pattern PATTERN = Pattern.compile("[\\p{Punct}–“”‑‘'…’—−·]");

    /**
     * The PROPERTIES field is used to set the StanfordCoreNLP pipeline.
     */
    private static final Properties PROPERTIES = new Properties() {{
        setProperty("annotators", "tokenize");
    }};

    /**
     * The STOPLIST_FILE_PATH field is used to set the path of the stoplist.
     */
    private static final String STOPLIST_FILE_PATH = "src/main/resources/stoplist.txt";

    static {
        RedwoodConfiguration.current()
                .clear()
                .apply();
    }

    /**
     * The PIPELINE field is used to process the text.
     */
    private static final StanfordCoreNLP PIPELINE = new StanfordCoreNLP(PROPERTIES);

    /**
     * The frequencyCounter field is used to count the frequency of the terms.
     */
    private final SynchronizedFrequencyCounter<String> frequencyCounter = new SynchronizedFrequencyCounter<>();

    /**
     * The stoplist field is used to store the stoplist.
     */
    private List<String> stoplist;

    /**
     * TermsDataSource constructor.
     * It is used to initialize the data source.
     */
    public TermsDataSource() {
        super(ID, new TxtEncoder());
        try {
            stoplist = Files.readAllLines(Paths.get(STOPLIST_FILE_PATH));
        } catch (Exception e) {
            stoplist = Collections.emptyList();
        }
    }

    /**
     * The set method is used to set the data source.
     * It is used to process the text and count the frequency of the terms.
     * Multi threading is used to speed up the process.
     *
     * @param context  the context
     * @param entities the list of entities
     * @throws Exception if an error occurs
     */
    @Override
    public void set(Context context, List<TermsDataEntity> entities) throws Exception {
        List<Future<?>> futures = new ArrayList<>(entities.size());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors());
        for (TermsDataEntity s : entities) {
            futures.add(executorService.submit(() -> PIPELINE.process(s.toString()
                            .toLowerCase())
                    .get(CoreAnnotations.TokensAnnotation.class)
                    .stream()
                    .map(CoreLabel::word)
                    .collect(Collectors.toSet())
                    .forEach(word -> {
                        if (!PATTERN.matcher(word).find() && !stoplist.contains(word)) {
                            frequencyCounter.add(word);
                        }
                    }))
            );
        }
        executorService.shutdown();
        for (Future<?> future : futures) {
            future.get();
        }
        try (FileWriter fileWriter = new FileWriter(context.outputTerms)) {
            fileWriter.write(encoder.encode(frequencyCounter.getMapSortedByValueAndKey(), context.countTerms));
        }
    }

}