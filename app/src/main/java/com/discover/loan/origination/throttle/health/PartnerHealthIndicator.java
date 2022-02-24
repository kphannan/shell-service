package com.discover.loan.origination.throttle.health;


import com.discover.loan.origination.throttle.config.PartnerServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;



/**
 * Base class for a downstream REST service health indicator.
 * Information from {@code PartnerServices} gives the address of a partner
 * REST service is used to invoke it's /health endpoint.
 */
@Component
@ConditionalOnEnabledHealthIndicator( "PartnerServices" )
@Log4j2
public abstract class PartnerHealthIndicator implements HealthIndicator
{

    @Override
    public Health health()
    {
        log.info( "Custom health indicator" );
        final Health.Builder status = Health.up();

        final PartnerServices.ServiceInfo serviceInfo = PartnerServices.getServiceInfo( getServiceName() );

        status.withDetail( "name", serviceInfo.getName() )
              .withDetail( "uri", serviceInfo.getUri() );

        // Call the /health endpoint of the named service

        return status.build();
    }

    /**
     * Derived class will supply the name of the service as defined in configuration.
     *
     * @return the name of the service.
     */
    protected abstract String getServiceName();
}