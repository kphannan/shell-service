package com.discover.loan.origination.throttle.service;


import com.discover.loan.origination.throttle.controller.model.ThrottleRequestVO;
import com.discover.loan.origination.throttle.controller.model.ThrottleResponse;
import com.discover.loan.origination.throttle.persistence.model.ApplicationThrottle;
import com.discover.loan.origination.throttle.persistence.repository.ThrottleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ThrottleService
{
    private final ThrottleRepository repository;


    @Autowired
    public ThrottleService( final ThrottleRepository repository )
    {
        this.repository = repository;
    }


    public Page<ApplicationThrottle> findAll( final Pageable paging )
    {
        return repository.findAll( paging );
    }

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
