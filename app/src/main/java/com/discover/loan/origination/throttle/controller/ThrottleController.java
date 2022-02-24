package com.discover.loan.origination.throttle.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import com.discover.loan.origination.throttle.controller.model.ThrottleRequestVO;
import com.discover.loan.origination.throttle.controller.model.ThrottleResponse;
import com.discover.loan.origination.throttle.persistence.model.ApplicationThrottle;
//import com.discover.loan.origination.throttle.persistence.repository.ThrottleRepository;
import com.discover.loan.origination.throttle.persistence.repository.ThrottleRepository;
import com.discover.loan.origination.throttle.service.ThrottleService;
import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller for application throttle.
 */
@RestController
@RequestMapping( "/loan/origination/throttle/eligible" )
@Log4j2
public class ThrottleController
{
    @SuppressWarnings( "PMD.BeanMembersShouldSerialize" )
    private final ThrottleRepository repository;
    private final ThrottleService    service;


//    ThrottleController( final ThrottleRepository repository )
//    {
//        this.repository = repository;
//    }
//    @Autowired
    public ThrottleController( final ThrottleService service, final ThrottleRepository repository )
    {
        this.service = service;
        this.repository = repository;
    }




    // ----- Create -----

    /**
     * Determine routing control settings for an application.
     *
     * @param request identifying attributes.
     * @return control properties for application processing routing.
     */
    @PostMapping( path = "" )
    public ResponseEntity<ThrottleResponse> rateApplication( @RequestBody final ThrottleRequestVO request )
    {
        // Handle the request
//        final ThrottleResponse response = service.rateApplication( request );
        final ThrottleResponse response = new ThrottleResponse();

        response.setApplicationId( request.getAppId() );
        response.setThrottleModelInd( request.getReengagementInd() );
        response.setThrottleStrategyInd( request.getOfferMatch() );
        response.setThrottlePriceInd( request.getOfferCode() );

        // Formulate the API response
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add( HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.toString() );
        URI location = null;
        try
        {
            location = new URI( String.format( "/loan/origination/throttle/eligible/%s", response.getApplicationId() ) );
        }
        catch ( URISyntaxException ex )
        {
            log.error( ex );
        }

        return ResponseEntity.created( location )
                             .headers( responseHeaders )
                             .body( response );

    }

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
        final Page<ApplicationThrottle> result =
            service == null ? repository.findAll( paging )
                            : service.findAll( paging );

        return ResponseEntity.ok( result );
    }


    // ----- Update -----

    // ----- Delete -----
}



