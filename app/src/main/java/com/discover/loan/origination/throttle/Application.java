package com.discover.loan.origination.throttle;

import com.discover.GeneratedIgnoreCoverage;

/**
 * Spring Boot Service application.
 */
public class Application
{
    /**
     * Temporary comment to keep PMD quiet.
     *
     * @return a constant string.
     */
    public String getGreeting()
    {
        return "Hello World!";
    }

    /**
     * Service entry point.
     *
     * @param args optional commandline arguments.
     */
    @GeneratedIgnoreCoverage
    public static void main( final String[] args )
    {
        System.out.println( new Application().getGreeting() );
    }
}
