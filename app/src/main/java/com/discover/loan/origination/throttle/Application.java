package com.discover.loan.origination.throttle;

import com.discover.GeneratedIgnoreCoverage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Spring Boot Service application.
 */
@SpringBootApplication
@EnableJpaRepositories( "com.discover.loan" )
public class Application
{

    public Application()
    {
        // Prevent instantiation other than by main().
    }

    /**
     * Service entry point.
     *
     * @param args optional commandline arguments.
     */
    @GeneratedIgnoreCoverage
    public static void main( final String[] args )
    {
        SpringApplication.run( Application.class, args );
    }
}
