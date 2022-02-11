package com.discover.loan.origination.throttle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import lombok.extern.log4j.Log4j2;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * BDD feature runner.
 */
@Log4j2
public class TestRunnerBDD
{
    @Test
    public void testParallel()
    {
        Results results = Runner.path("classpath:com")
                .outputCucumberJson( true )
                .karateEnv( "local" )
                .debugMode( true )
                .parallel( 5 );

        generateReport( results.getReportDir() );

        assertTrue(  results.getFailCount() == 0
                   , results.getErrorMessages() );


    }


    public static void generateReport( final String karateOutputPath )
    {
        // log.error( String.format( "outputPath: '%s'", karateOutputPath ) );
        Collection<File> jsonFiles = FileUtils.listFiles( new File( karateOutputPath ), new String[] { "json" }, true );
        List<String> jsonPaths = new ArrayList<>( jsonFiles.size() );

        jsonFiles.forEach( file -> jsonPaths.add( file.getAbsolutePath() ) );

        Configuration config = new Configuration( new File( "target"), "demo" );
        ReportBuilder reportBuilder = new ReportBuilder( jsonPaths, config );

        // log.info( config );
        // log.info( jsonPaths );
        // log.info( reportBuilder.toString() );

        reportBuilder.generateReports();
    }

}