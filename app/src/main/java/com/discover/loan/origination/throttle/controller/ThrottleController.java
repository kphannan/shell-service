package com.discover.loan.origination.throttle.controller;

import com.discover.loan.origination.throttle.persistence.model.ApplicationThrottle;
import com.discover.loan.origination.throttle.persistence.repository.ThrottleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller for application throttle.
 */
@RestController
@RequestMapping( "/loan/origination/throttle/eligible" )
public class ThrottleController
{
    @SuppressWarnings( "PMD.BeanMembersShouldSerialize" )
    private final ThrottleRepository repository;


    ThrottleController( final ThrottleRepository repository )
    {
        this.repository = repository;
    }




    // ----- Create -----

    // ----- Retrieve -----
    /**
     * Retrieve a paged list of all applications processed and their associated
     * deceision data.
     *
     * @param paging the desired {@code Page}.
     * @return the requested {@code Page} of results.  The page may contain no results.
     */
    @GetMapping( path = "" )
    public ResponseEntity<Page<ApplicationThrottle>> all( final Pageable paging )
    {
        final Page<ApplicationThrottle> result = repository.findAll( paging );

        return ResponseEntity.ok( result );
    }


    // ----- Update -----

    // ----- Delete -----
}



