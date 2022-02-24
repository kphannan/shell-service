package com.discover.loan.origination.throttle.health;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.discover.loan.origination.throttle.config.PartnerServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * Unit tests for PartnerHealthIndicator.
 */
@SuppressWarnings( "PMD.MethodNamingConventions" )
@ExtendWith( SpringExtension.class )
@EnableConfigurationProperties( PartnerServices.class )
@TestPropertySource( "classpath:PartnerServiceTestProperties.properties" )
class PartnerHealthIndicatorTest
{
    @Test
    void partner1Health_health_generatesHealth()
    {
        final PartnerHealthIndicator healthIndicator = new Service1HealthIndicator();

        final Health health = healthIndicator.health();

        assertAll(  () -> assertEquals( Status.UP, health.getStatus() )
                  , () -> assertEquals( "service1", health.getDetails().get( "name" ) )
                  , () -> assertEquals(  "http://host1.some.domain/base/path/without/leading/slash"
                                       , health.getDetails().get( "uri" ).toString() )
        );
    }


    @Test
    void partner2Health_health_generatesHealth()
    {
        final PartnerHealthIndicator healthIndicator = new Service2HealthIndicator();

        final Health health = healthIndicator.health();

        assertAll(  () -> assertEquals( Status.UP, health.getStatus() )
                  , () -> assertEquals( "service2", health.getDetails().get( "name" ) )
                  , () -> assertEquals(  "https://host2.some.domain/service/path"
                                       , health.getDetails().get( "uri" ).toString() )
        );
    }

}
