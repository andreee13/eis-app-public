package it.unipd.dei.eis.domain.models;

public class TermFrequency implements IModel {
    final public String term;
    final public int frequency;

    public TermFrequency(String term, int frequency) {
        this.term = term;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "TermFrequency{" +
                "term='" + term + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
