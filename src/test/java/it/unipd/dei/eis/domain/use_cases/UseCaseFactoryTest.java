package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.enums.UseCases;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the use case factory.
 */
class UseCaseFactoryTest {

    /**
     * Test the create method.
     */
    @Test
    void create() {
        assertNotNull(UseCaseFactory.create(UseCases.EXTRACT));
        assertNotNull(UseCaseFactory.create(UseCases.DOWNLOAD));
        assertNotNull(UseCaseFactory.create(UseCases.DOWNLOAD_AND_EXTRACT));
    }
}