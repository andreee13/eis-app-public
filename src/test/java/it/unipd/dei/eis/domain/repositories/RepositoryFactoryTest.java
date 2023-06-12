package it.unipd.dei.eis.domain.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the repository factory.
 */
class RepositoryFactoryTest {

    /**
     * Test the create method.
     */
    @Test
    void create() {
        Repository<?, ?, ?> theGuardianRepository = RepositoryFactory.create("theguardian");
        assertTrue(theGuardianRepository instanceof TheGuardianRepository);
        assertThrows(IllegalArgumentException.class, () -> RepositoryFactory.create("not a valid repository"));
    }
}