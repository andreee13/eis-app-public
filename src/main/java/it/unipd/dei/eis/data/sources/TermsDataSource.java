package it.unipd.dei.eis.data.sources;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;
import it.unipd.dei.eis.core.utils.SynchronizedStringFrequencyCounter;
import it.unipd.dei.eis.data.entities.ArticleTermsDataEntity;
import it.unipd.dei.eis.presentation.Context;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static it.unipd.dei.eis.core.constants.DefaultSettings.TXT_FILE_NAME;

/**
 * TermsDataSource is the data source for the terms.
 * It contains the data structure of the terms.
 */
public class TermsDataSource extends DataSource<ArticleTermsDataEntity> {

    /**
     * The ID of the data source.
     */
    public static final String ID = "TERMS";
    /**
     * The PATTERN is used to check if a word is a punctuation or other special symbols.
     */
    private static final Pattern PATTERN = Pattern.compile("[\\p{Punct}–“”‑‘'…’]");

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
    /**
     * The pipeline field is used to process the text.
     */
    private static StanfordCoreNLP pipeline;
    /**
     * The frequencyCounter field is used to count the frequency of the terms.
     */
    private final SynchronizedStringFrequencyCounter frequencyCounter = new SynchronizedStringFrequencyCounter();
    /**
     * The stoplist field is used to store the stoplist.
     */
    private List<String> stoplist;

    /**
     * TermsDataSource constructor.
     * It is used to initialize the data source.
     */
    public TermsDataSource() {
        super(ID);
        RedwoodConfiguration.current()
                .clear()
                .apply();
        pipeline = new StanfordCoreNLP(PROPERTIES);
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
    public void set(Context context, List<ArticleTermsDataEntity> entities) throws Exception {
        List<Future<Annotation>> futures = new ArrayList<>(entities.size());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors());
        for (ArticleTermsDataEntity s : entities) {
            futures.add(executorService.submit(() -> pipeline.process(s.toString()
                    .toLowerCase())));
        }
        for (Future<Annotation> future : futures) {
            future.get()
                    .get(CoreAnnotations.TokensAnnotation.class)
                    .stream()
                    .map(CoreLabel::word)
                    .filter(word -> !PATTERN.matcher(word)
                            .find() && !stoplist.contains(word))
                    .forEach(frequencyCounter::add);
        }
        executorService.shutdown();
        Map<String, Integer> map = frequencyCounter.getMapSortedByValueAndKey();
        List<String> keys = frequencyCounter.getMapSortedByValueAndKey()
                .keySet()
                .stream()
                .limit(context.countTerms)
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            stringBuilder.append(key)
                    .append(" ")
                    .append(map.get(key))
                    .append("\n");
        }
        try (FileWriter fileWriter = new FileWriter(TXT_FILE_NAME)) {
            fileWriter.write(stringBuilder.toString());
        }
    }

}