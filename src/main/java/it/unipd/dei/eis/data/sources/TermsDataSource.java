package it.unipd.dei.eis.data.sources;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import it.unipd.dei.eis.data.entities.ArticleTermsDataEntity;
import it.unipd.dei.eis.presentation.Context;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

public class TermsDataSource extends DataSource<ArticleTermsDataEntity> {
    private static final Pattern pattern = Pattern.compile("\\p{Punct}");
    private static final Properties props = new Properties() {{
        setProperty("annotators", "tokenize");
    }};
    private static final StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    private final SynchronizedTermsFrequencyCounter frequencyCounter = new SynchronizedTermsFrequencyCounter();

    public TermsDataSource() {
        super("TERMS");
    }

    @Override
    public void set(Context context, List<ArticleTermsDataEntity> entities) throws Exception {
        List<Future<Annotation>> futures = new ArrayList<>(entities.size());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (ArticleTermsDataEntity s : entities) {
            futures.add(executorService.submit(() -> pipeline.process(s.toString())));
        }
        for (Future<Annotation> future : futures) {
            future.get().get(CoreAnnotations.TokensAnnotation.class).stream().map(CoreLabel::word).filter(word -> !pattern.matcher(word).matches()).forEach(frequencyCounter::add);
        }
        executorService.shutdown();
        //TODO: save to file
        System.out.println(frequencyCounter.getMapSortedByValueAndKey());
    }

}

class SynchronizedTermsFrequencyCounter {
    final private Map<String, Integer> map = new HashMap<>();

    public synchronized void add(String word) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public Map<String, Integer> getMapSortedByValueAndKey() {
        Map<String, Integer> map = new TreeMap<>(Comparator.comparingInt(this.map::get).reversed().thenComparing(Object::toString));
        map.putAll(this.map);
        return map;
    }
}