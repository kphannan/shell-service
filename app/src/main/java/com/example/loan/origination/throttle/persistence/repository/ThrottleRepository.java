package com.example.loan.origination.throttle.persistence.repository;

import com.example.loan.origination.throttle.persistence.model.ApplicationThrottle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Definition of access to the Throttle Repository table.
 */
public interface ThrottleRepository extends PagingAndSortingRepository<ApplicationThrottle, String>
{

    /**
     * Retrieve all {@code ApplicationThrottle} records by {@code Page}.
     *
     * @param paging the {@code Page} criteria.
     * @return the located page, whose body contains the found records.
     */
    @Override
    Page<ApplicationThrottle> findAll( Pageable paging );

}
