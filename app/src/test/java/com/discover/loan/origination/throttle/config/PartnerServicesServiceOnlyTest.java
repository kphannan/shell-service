package com.discover.loan.origination.throttle.config;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for ModelThreshold class.
 */
@SuppressWarnings( {  "PMD.JUnitTestContainsTooManyAsserts"
                    , "PMD.BeanMembersShouldSerialize"
                    , "PMD.UnnecessaryAnnotationValueElement" } )
@ExtendWith( SpringExtension.class )
@EnableConfigurationProperties( value = PartnerServices.class )
@TestPropertySource( "classpath:PartnerServiceServiceOnlyTestProperties.properties" )
class PartnerServicesServiceOnlyTest
{
    @Autowired
    PartnerServices partnerServices;

    @Test
    @SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.BeanMembersShouldSerialize" } )
    void partnerServices_serviceOnlyPropertyBinding_establishesDefaultState()
    {
        assertAll(  () -> assertNotNull( partnerServices )
                  , () -> assertNotNull( partnerServices.getServices() )
                  , () -> assertNull( partnerServices.getSoapServices() )

                  // --- Services ---
                  //   - service2
                  , () -> assertEquals( "service2", partnerServices.getServices().get( 0 ).getName() )
                  , () -> assertEquals( "host2.some.domain", partnerServices.getServices().get( 0 ).getHost() )
                  , () -> assertNull( partnerServices.getServices().get( 0 ).getPort() )
                  , () -> assertEquals( "/service/path", partnerServices.getServices().get( 0 ).getPath() )
                  , () -> assertNotNull( partnerServices.getServices().get( 0 ).getCertificate() )
                  , () -> assertEquals(  "foo.jks"
                                       , partnerServices.getServices().get( 0 ).getCertificate().getKeystoreFilename() )
                  , () -> assertEquals(  "keyuser"
                                       , partnerServices.getServices().get( 0 ).getCertificate().getUsername() )
                  , () -> assertEquals(  "keypass"
                                       , partnerServices.getServices().get( 0 ).getCertificate().getPassword() )
                  , () -> assertEquals(  "https://host2.some.domain/service/path"
                                       , partnerServices.getServices().get( 0 ).getUri().toString() )

        );
    }


}





