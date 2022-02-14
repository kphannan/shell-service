package com.discover.loan.origination.throttle.health;


import com.discover.loan.origination.throttle.config.PartnerServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;




@Component
@ConditionalOnEnabledHealthIndicator("PartnerServices")
@Log4j2
public class PartnerHealthIndicator implements HealthIndicator
{

    @Override
    public Health health()
    {
        log.info( "Custom health indicator" );
        Health.Builder status = Health.up();

        status.withDetail( "name", "service1" )
              .withDetail( "uri", PartnerServices.getServiceInfo( "service1" ).getUri() );

        return status.build();
    }

}