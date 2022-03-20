package com.example.loan.origination.throttle.config;


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
@TestPropertySource( "classpath:PartnerServiceSoapServiceOnlyTestProperties.properties" )
class PartnerServicesSoapServiceOnlyTest
{
    @Autowired
    PartnerServices partnerServices;

    @Test
    void partnerServicesSoap_propertyBinding_establishesDefaultState()
    {
        assertAll(  () -> assertNotNull( partnerServices )
                  , () -> assertNull( partnerServices.getServices() )

                  // --- Services ---
                  , () -> assertNotNull( partnerServices.getSoapServices() )
                  //   - service2
                  , () -> assertEquals( "soapservice2", partnerServices.getSoapServices().get( 0 ).getName() )
                  , () -> assertEquals( "soaphost2", partnerServices.getSoapServices().get( 0 ).getHost() )
                  , () -> assertEquals( "4567", partnerServices.getSoapServices().get( 0 ).getPort() )
                  , () -> assertEquals( "/secure/soap", partnerServices.getSoapServices().get( 0 ).getPath() )
                  , () -> assertNotNull( partnerServices.getSoapServices().get( 0 ).getCertificate() )
                  , () -> assertEquals( "soaponly.jks", partnerServices.getSoapServices()
                                                            .get( 0 ).getCertificate().getKeystoreFilename() )
                  , () -> assertEquals( "soaponlyuser", partnerServices.getSoapServices()
                                                            .get( 0 ).getCertificate().getUsername() )
                  , () -> assertEquals( "soaponlypass", partnerServices.getSoapServices()
                                                            .get( 0 ).getCertificate().getPassword() )

                  , () -> assertEquals( 444, partnerServices.getSoapServices()
                                                  .get( 0 ).getClientPolicy().getConnectTimeout() )
                  , () -> assertEquals( 111, partnerServices.getSoapServices()
                                                  .get( 0 ).getClientPolicy().getMaxElapsedTime() )
                  , () -> assertEquals( 222, partnerServices.getSoapServices()
                                                  .get( 0 ).getClientPolicy().getTimeout() )
                  , () -> assertEquals( 333, partnerServices.getSoapServices()
                                                  .get( 0 ).getClientPolicy().getMaxRetries() )
                  , () -> assertEquals(  "https://soaphost2:4567/secure/soap"
                                       , partnerServices.getSoapServices().get( 0 ).getUri().toString() )

        );
    }


}





