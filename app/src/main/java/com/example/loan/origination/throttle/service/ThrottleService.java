package com.example.loan.origination.throttle.service;


import com.example.loan.origination.throttle.controller.model.ThrottleRequestVO;
import com.example.loan.origination.throttle.controller.model.ThrottleResponse;
import com.example.loan.origination.throttle.persistence.model.ApplicationThrottle;
import com.example.loan.origination.throttle.persistence.repository.ThrottleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/**
 * Business logic.
 */
@Service
public class ThrottleService
{
    private final ThrottleRepository repository;


    /**
     * Create a service instance autowired to a repository.
     *
     * @param repository the persistent repository.
     */
    @Autowired
    public ThrottleService( final ThrottleRepository repository )
    {
        this.repository = repository;
    }


    /**
     * Find all entries based on the search and page criteria.
     *
     * @param paging page and search criterial.
     * @return the requested result page.
     */
    public Page<ApplicationThrottle> findAll( final Pageable paging )
    {
        return repository.findAll( paging );
    }

    /**
     * Calculate the rating.
     *
     * @param request the input.
     * @return the output.
     */
    public ThrottleResponse rateApplication( final ThrottleRequestVO request )
    {
        final ThrottleResponse response = new ThrottleResponse();

        response.setApplicationId( request.getAppId() );
        response.setThrottleModelInd( request.getReengagementInd() );
        response.setThrottleStrategyInd( request.getOfferMatch() );
        response.setThrottlePriceInd( request.getOfferCode() );

        return response;
    }
}
