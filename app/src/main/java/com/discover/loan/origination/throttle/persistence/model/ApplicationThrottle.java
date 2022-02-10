package com.discover.loan.origination.throttle.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * Entity definition for the Throttle Status table.
 */
@Entity
@Table( name = "app_throttle" )
@Data
@SuppressWarnings( "PMD.TooManyFields" )
public class ApplicationThrottle
{
    @Id
    @Column( name = "application_id" )
    private String applicationId;

    @Column( name = "model_ind" )
    private Boolean modelIndicator;

    @Column( name = "strategy_ind" )
    private Boolean strategyIndicator;

    @Column( name = "pricing_ind" )
    private Boolean pricingIndicator;
}
