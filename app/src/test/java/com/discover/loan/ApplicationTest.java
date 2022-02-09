
package com.discover.loan;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * REST service application unit test.
 */
class ApplicationTest
{
    @Test
    void appHasAGreeting()
    {
        final Application classUnderTest = new Application();
        assertNotNull( classUnderTest.getGreeting(), "app should have a greeting" );
    }
}
