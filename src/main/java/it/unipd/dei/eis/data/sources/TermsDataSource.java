package it.unipd.dei.eis.data.sources;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;
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

import static it.unipd.dei.eis.core.constants.DefaultSettings.OUTPUT_FILE_TXT;

/**
 * TermsDataSource is the data source for the terms.
 * It contains the data structure of the terms.
 */
public class TermsDataSource extends DataSource<ArticleTermsDataEntity> {

    /**
     * The pattern is used to check if a word is a punctuation or other special symbols.
     */
    private static final Pattern pattern = Pattern.compile("[\\p{Punct}–“”‑‘'…]");

    /**
     * The props field is used to set the StanfordCoreNLP pipeline.
     */
    private static final Properties props = new Properties() {{
        setProperty("annotators", "tokenize");
    }};

    /**
     * The pipeline field is used to process the text.
     */
    private static StanfordCoreNLP pipeline;

    /**
     * The frequencyCounter field is used to count the frequency of the terms.
     */
    private final SynchronizedTermsFrequencyCounter frequencyCounter = new SynchronizedTermsFrequencyCounter();

    /**
     * The stoplist field is used to store the stoplist.
     */
    private List<String> stoplist;

    /**
     * TermsDataSource constructor.
     * It is used to initialize the data source.
     */
    public TermsDataSource() {
        super("TERMS");
        RedwoodConfiguration.current()
                .clear()
                .apply();
        pipeline = new StanfordCoreNLP(props);
        try {
            stoplist = Files.readAllLines(Paths.get("src/main/resources/stoplist.txt"));
        } catch (Exception e) {
            stoplist = Collections.emptyList();
        }
    }

    /**
     * The set method is used to set the data source.
     * @param context the context
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
                    .filter(word -> !pattern.matcher(word)
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
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_TXT)) {
            fileWriter.write(stringBuilder.toString());
        }
    }

}

/**
 * SynchronizedTermsFrequencyCounter is the class used to count the frequency of the terms.
 */
class SynchronizedTermsFrequencyCounter {

    /**
     * The map field is used to store the terms and their frequency.
     */
    final private Map<String, Integer> map = new HashMap<>();

    /**
     * The add method is used to add a term to the map.
     * @param word the term
     */
    public synchronized void add(String word) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    /**
     * The getMapSortedByValueAndKey method is used to sort the map by value and key.
     * @return the sorted map
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public Map<String, Integer> getMapSortedByValueAndKey() {
        Map<String, Integer> map = new TreeMap<>(Comparator.comparingInt(this.map::get)
                .reversed()
                .thenComparing(Object::toString));
        map.putAll(this.map);
        return map;
    }
}