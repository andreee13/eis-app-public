package it.unipd.dei.eis.domain.models;

import it.unipd.dei.eis.core.utils.DateParser;
import junit.framework.TestCase;

public class ArticleTest extends TestCase {

    /**
     * Test the article equals method.
     */
    public void testEquals() {
        Article article1 = new Article(
                "id",
                "title",
                "body",
                "url",
                DateParser.tryParse("2023-01-01"),
                "source"
        );
        Article article2 = new Article(
                "id",
                "title",
                "body",
                "url",
                DateParser.tryParse("2023-01-01"),
                "source"
        );
        assertEquals(article1, article2);
    }
}