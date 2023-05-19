package it.unipd.dei.eis.data.sources;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import it.unipd.dei.eis.data.entities.ArticleTermsDataEntity;
import it.unipd.dei.eis.presentation.Context;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TermsDataSource extends DataSource<ArticleTermsDataEntity> {
    private static final Pattern pattern = Pattern.compile("\\p{Punct}");
    private static final Properties props = new Properties() {{
        setProperty("annotators", "tokenize");
    }};
    private static final StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    private final WordFrequencyCounter frequencyCounter = new WordFrequencyCounter();

    public TermsDataSource() {
        super("TERMS");
    }

    @Override
    public void set(Context context, List<ArticleTermsDataEntity> entities) throws Exception {
        List<Future<Annotation>> futures = new ArrayList<>();
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (ArticleTermsDataEntity s : entities) {
            Callable<Annotation> annotationTask = () -> pipeline.process(s.toString());
            Future<Annotation> future = executorService.submit(annotationTask);
            futures.add(future);
        }
        for (Future<Annotation> future : futures) {
            Annotation annotation = future.get();
            frequencyCounter.addAll(annotation.get(CoreAnnotations.TokensAnnotation.class).stream().map(CoreLabel::word).filter(word -> !pattern.matcher(word).matches()).collect(Collectors.toList()));
        }
        executorService.shutdown();
        //TODO: save to file
        System.out.println(frequencyCounter.getMapSortedByValueAndKey());
    }

}

class WordFrequencyCounter {
    final private Map<String, Integer> map = new HashMap<>();

    public synchronized void add(String word) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public synchronized void addAll(Collection<String> collection) {
        for (String element : collection) {
            add(element);
        }
    }

    public Map<String, Integer> getMapSortedByValueAndKey() {
        Map<String, Integer> map = new TreeMap<>(Comparator.comparingInt(this.map::get).reversed().thenComparing(Object::toString));
        map.putAll(this.map);
        return map;
    }

}