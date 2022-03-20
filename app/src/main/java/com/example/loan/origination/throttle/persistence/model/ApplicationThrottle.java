package com.example.loan.origination.throttle.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



/*
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
    @SequenceGenerator( name = "app_throttle_sequence"
                       , sequenceName = "app_throttle_sequence"
                       , allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE
                    , generator = "app_throttle_sequence" )
 */

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
