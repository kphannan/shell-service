package com.example.support;

import org.assertj.core.api.AbstractAssert;
import org.springframework.data.domain.Sort;


/**
 * Custom assertion for verification of a Sort object.
 */
public class SortAssert extends AbstractAssert<SortAssert, Sort>
{

    SortAssert( final Sort sort )
    {
        super( sort, SortAssert.class );
    }

    public static SortAssert assertThat( final Sort actual )
    {
        return new SortAssert( actual );
    }

    /**
     * Assert the sort is for the desired attribute and collation order.
     *
     * @param field the attribute to sort on.
     * @param direction the intended collation sequence.
     * @return this for assertion chaining.
     */
    public SortAssert hasSort( final String field, final Sort.Direction direction )
    {
        final Sort.Order actualOrder = actual.getOrderFor( field );

        if ( actualOrder == null )
        {
            failWithMessage( "expected sort for field <%s> to be <%s> but was null", field, direction );
        } else if ( actualOrder.getDirection() != direction )
        {
            failWithMessage(  "expected sort for field <%s> to be <%s> but was <%s>"
                            , field, direction, actualOrder.getDirection() );
        }

        return this;
    }
}