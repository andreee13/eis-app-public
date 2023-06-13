package it.unipd.dei.eis.data.sources;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;
import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.utils.SynchronizedFrequencyCounter;
import it.unipd.dei.eis.data.codecs.TxtEncoder;
import it.unipd.dei.eis.data.entities.TermsDataEntity;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TermsDataSource is the data source for the terms.
 * It contains the data structure of the terms.
 */
public class TermsDataSource extends DataSource<TermsDataEntity, Map<String, Integer>> {

    /**
     * The ID of the data source.
     */
    public static final String ID = "TERMS";
    /**
     * The PATTERN is used to check if a word is a punctuation or other special symbols.
     */
    private static final Pattern PATTERN = Pattern.compile("[\\p{Punct}–“”‑‘'…’—−·]");

    /**
     * The STOPLIST_FILE_NAME field is used to store the name of the file containing the stoplist.
     */
    private static final String STOPLIST_FILE_NAME = "stoplist.txt";

    static {
        RedwoodConfiguration.current()
                .clear()
                .apply();
    }

    /**
     * The stoplist field is used to store the stoplist.
     */
    private final List<String> stoplist;

    /**
     * TermsDataSource constructor.
     * It is used to initialize the data source.
     */
    public TermsDataSource() {
        super(ID, new TxtEncoder());
        stoplist = getStoplist();
    }

    /**
     * The getStoplist method is used to get the stoplist from the file.
     *
     * @return the stoplist as a list of strings or an empty list if an error occurs
     */
    private List<String> getStoplist() {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(
                                    getClass().getClassLoader().getResourceAsStream(STOPLIST_FILE_NAME)
                            )
                    )
            );
            List<String> stoplist = bufferedReader.lines()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            bufferedReader.close();
            return stoplist;
        } catch (Exception e) {
            return Collections.emptyList();
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
    void setData(Context context, List<TermsDataEntity> entities) throws Exception {
        Properties properties = new Properties();
        if (context.lemma) {
            properties.setProperty("annotators", "tokenize, pos, lemma");
        } else {
            properties.setProperty("annotators", "tokenize");
        }
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        ExecutorService executorService = Executors.newWorkStealingPool();
        SynchronizedFrequencyCounter<String> frequencyCounter = new SynchronizedFrequencyCounter<>();
        for (TermsDataEntity s : entities) {
            executorService.execute(() -> pipeline.process(s.toString())
                    .get(CoreAnnotations.TokensAnnotation.class)
                    .stream()
                    .map(context.lemma ? CoreLabel::lemma : CoreLabel::word)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet())
                    .forEach(term -> {
                        if (!PATTERN.matcher(term).find() && !stoplist.contains(term)) {
                            frequencyCounter.add(term);
                        }
                    }));
        }
        executorService.shutdown();
        if (!executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
            executorService.shutdownNow();
            throw new Exception("Maximum time exceeded, the process has been interrupted.");
        }
        try (FileWriter fileWriter = new FileWriter(context.outputTerms)) {
            fileWriter.write(encoder.encode(frequencyCounter.getMapSortedByValueAndKey(), context.countTerms));
        }
    }
}
