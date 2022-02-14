package com.discover.loan.origination.throttle.health;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;


/**
 * Unit tests for PartnerHealthIndicator.
 */
@SuppressWarnings( "PMD.MethodNamingConventions" )
class PartnerHealthIndicatorTest
{
    @Test
    void partnerHealth_health_generatesHealth()
    {
        final PartnerHealthIndicator healthIndicator = new PartnerHealthIndicator();

        final Health health = healthIndicator.health();

        assertAll(  () -> assertEquals( Status.UP, health.getStatus() )
                  , () -> assertEquals( "service1", health.getDetails().get( "name" ) )
                  , () -> assertEquals(  "http://host1.some.domain/base/path/without/leading/slash"
                                       , health.getDetails().get( "uri" ) )
        );
    }
}
