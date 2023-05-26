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

public class TermsDataSource extends DataSource<ArticleTermsDataEntity> {
    private static final Pattern pattern = Pattern.compile("[\\p{Punct}–“”‑‘'…]");
    private static final Properties props = new Properties() {{
        setProperty("annotators", "tokenize");
    }};
    private static StanfordCoreNLP pipeline;
    private final SynchronizedTermsFrequencyCounter frequencyCounter = new SynchronizedTermsFrequencyCounter();
    private List<String> stoplist;

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

class SynchronizedTermsFrequencyCounter {
    final private Map<String, Integer> map = new HashMap<>();

    public synchronized void add(String word) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public Map<String, Integer> getMapSortedByValueAndKey() {
        Map<String, Integer> map = new TreeMap<>(Comparator.comparingInt(this.map::get)
                .reversed()
                .thenComparing(Object::toString));
        map.putAll(this.map);
        return map;
    }
}