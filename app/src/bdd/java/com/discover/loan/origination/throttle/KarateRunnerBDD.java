package com.discover.loan.origination.throttle;

import com.intuit.karate.junit5.Karate;
import lombok.extern.log4j.Log4j2;

/**
 * BDD feature runner.
 */
@Log4j2
public class KarateRunnerBDD
{
    /**
     * Execute all feature files from this package level and downward.
     *
     * @return something that isn't used.
     */
    @Karate.Test
    public Karate testAll()
    {
        log.info( "Run Karate BDD Tests" );

        return new Karate().run().relativeTo( getClass() );
    }

}