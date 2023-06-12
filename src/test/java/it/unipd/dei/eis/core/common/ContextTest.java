package it.unipd.dei.eis.core.common;

import it.unipd.dei.eis.core.constants.Outputs;
import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.IntegerParser;
import it.unipd.dei.eis.domain.use_cases.UseCase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @Test
    void fromCommandLine() {
        Context context = new Context(UseCases.EXTRACT, "src/test/resources/file.csv", "articles.json", "terms.txt", "", 10, 10, new Date(2020-1-1), new Date(2021-1-1), "abc123");
        if (context.source == null) {
            throw new IllegalArgumentException("The source option is required");
        }

        if (context.countArticles < 0) {
            throw new IllegalArgumentException("The count-articles option must be a positive integer");
        }
        if (context.countTerms < 0) {
            throw new IllegalArgumentException("The count-terms option must be a positive integer");
        }
        if (context.fromDate != null && context.toDate != null && context.fromDate.after(context.toDate)) {
            throw new IllegalArgumentException("The from-date option must be before the to-date option");
        }
        if (context.outputArticles != null && !Outputs.isArticlesOutput(context.outputArticles)) {
            throw new IllegalArgumentException("The output-articles option must have one of the following extensions: " + Arrays.toString(Outputs.ARTICLES_OUTPUTS));
        }
        if (context.outputTerms != null && !Outputs.isTermsOutput(context.outputTerms)) {
            throw new IllegalArgumentException("The output-terms option must have one of the following extensions: " + Arrays.toString(Outputs.TERMS_OUTPUTS));
        }
    }
}