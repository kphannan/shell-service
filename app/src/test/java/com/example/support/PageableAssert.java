package com.example.support;

import java.util.Objects;

import org.assertj.core.api.AbstractAssert;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * Custom assertion to verify attributes of {@code Pageable} are set properly.
 */
public class PageableAssert extends AbstractAssert<PageableAssert, Pageable>
{

    PageableAssert( final Pageable pageable )
    {
        super( pageable, PageableAssert.class );
    }

    public static PageableAssert assertThat( final Pageable actual )
    {
        return new PageableAssert( actual );
    }


    /**
     * Verifiy the desired page size has been set.
     *
     * @param expectedPageSize desired page size
     * @return this assertion for chaining.
     */
    public PageableAssert hasPageSize( final int expectedPageSize )
    {
        if ( !Objects.equals( actual.getPageSize(), expectedPageSize ) )
        {
            failWithMessage( "expected page size to be <%s> but was <%s>", expectedPageSize, actual.getPageSize() );
        }

        return this;
    }


    /**
     * Verify the desired page is the one returned.
     *
     * @param expectedPageNumber the desired page number
     * @return this assertion for chaining.
     */
    public PageableAssert hasPageNumber( final int expectedPageNumber )
    {
        if ( !Objects.equals( actual.getPageNumber(), expectedPageNumber ) )
        {
            failWithMessage( "expected page number to be <%s> but was <%s>", expectedPageNumber, actual.getPageNumber() );
        }

        return this;
    }

    /**
     * Evaluate the sort fragment.
     *
     * @param field name of the attribute sorted by.
     * @param direction the intended sort direction.
     * @return this assertion for method chaining.
     */
    public PageableAssert hasSort( final String field, final Sort.Direction direction )
    {
        final Sort.Order actualOrder = actual.getSort().getOrderFor( field );

        if ( actualOrder == null )
        {
            failWithMessage( "expected sort for field <%s> to be <%s> but was null", field, direction );
        }
        else if ( actualOrder.getDirection() != direction )
        {
            failWithMessage( "expected sort for field <%s> to be <%s> but was <%s>"
                            , field, direction, actualOrder.getDirection() );
        }

        return this;
    }
}