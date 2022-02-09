package com.discover;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Custom annotation that can be applied to a method or class to
 * be removed from code coverage analysis.  Use only when necessary.
 */
@Documented
@Retention( RUNTIME )
@Target( { TYPE, METHOD } )
public @interface GeneratedIgnoreCoverage
{
    // no content needed
}