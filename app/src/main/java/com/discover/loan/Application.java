package com.discover.loan;

/**
 * Spring Boot Service application.
 */
public class Application
{
    public String getGreeting()
    {
        return "Hello World!";
    }

    /**
     * Service entry point.
     *
     * @param args optional commandline arguments.
     */
    public static void main( final String[] args )
    {
        System.out.println( new Application().getGreeting() );
    }
}
