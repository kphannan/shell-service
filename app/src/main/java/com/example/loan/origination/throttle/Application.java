package com.example.loan.origination.throttle;

import com.example.GeneratedIgnoreCoverage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Spring Boot Service application.
 */
@SpringBootApplication
@EnableJpaRepositories( "com.example.loan" )
// The main() entry point of the service, does not adhere to the PMD suggestions.
@SuppressWarnings( { "PMD.UseUtilityClass" } )
public class Application
{

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
