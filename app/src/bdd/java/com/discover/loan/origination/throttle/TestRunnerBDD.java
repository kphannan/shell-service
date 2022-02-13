package com.discover.loan.origination.throttle;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;


/**
 * BDD feature runner.
 */
class TestRunnerBDD
{
    @Test
    void testParallel()
    {
        final Results results =
            Runner.path( "classpath:com" )
                    .outputCucumberJson( true )
                    .karateEnv( "local" )
                    .debugMode( true )
                    .parallel( 5 );

        generateReport( results.getReportDir() );

        assertTrue(  results.getFailCount() == 0
                   , results.getErrorMessages() );


    }


    private static void generateReport( final String karateOutputPath )
    {
        final Collection<File> jsonFiles = FileUtils.listFiles( new File( karateOutputPath ), new String[] { "json" }, true );
        final List<String> jsonPaths = new ArrayList<>( jsonFiles.size() );

        jsonFiles.forEach( file -> jsonPaths.add( file.getAbsolutePath() ) );

        final Configuration config = new Configuration( new File( "target" ), "demo" );
        final ReportBuilder reportBuilder = new ReportBuilder( jsonPaths, config );

        reportBuilder.generateReports();
    }

}