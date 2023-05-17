package it.unipd.dei.eis;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//TODO: remove

public class Dev {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\p{Punct}");
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        WordFrequencyCounter frequencyCounter = new WordFrequencyCounter();
        WordCounter[] wordCounters = new WordCounter[100];
        for (int i = 0; i < wordCounters.length; i++) {
            wordCounters[i] = new WordCounter(frequencyCounter, pattern, pipeline, "lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." + Integer.parseInt(String.valueOf(Math.round(Math.random() * 1000))));
        }
        for (WordCounter wordCounter : wordCounters) {
            wordCounter.start();
        }
        try {
            for (WordCounter wordCounter : wordCounters) {
                wordCounter.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(frequencyCounter.getMapSortedByValueAndKey());
    }
}

class WordCounter extends Thread {
    final private WordFrequencyCounter frequencyCounter;
    final private Pattern pattern;
    final private StanfordCoreNLP pipeline;
    final private String text;

    WordCounter(WordFrequencyCounter frequencyCounter, Pattern pattern, StanfordCoreNLP pipeline, String text) {
        this.frequencyCounter = frequencyCounter;
        this.pattern = pattern;
        this.pipeline = pipeline;
        this.text = text;
    }

    @Override
    public void run() {
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);
        frequencyCounter.addAll(doc.tokens().stream().map(CoreLabel::word).filter(word -> !pattern.matcher(word).matches()).collect(Collectors.toSet()));
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