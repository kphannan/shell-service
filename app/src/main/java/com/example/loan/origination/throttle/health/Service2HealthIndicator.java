package com.example.loan.origination.throttle.health;


import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.stereotype.Component;




/**
 * Service 2 HealthIndicator.
 */
@Component
@ConditionalOnEnabledHealthIndicator( "PartnerServices" )
public class Service2HealthIndicator extends PartnerHealthIndicator
{

    @Override
    protected String getServiceName()
    {
        return "service2";
    }

}