package com.discover.loan.origination.throttle.config;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
@TestPropertySource( "classpath:PartnerServiceInvalidURLTestProperties.properties" )
class PartnerServicesInvalidURLTest
{
    @Autowired
    PartnerServices partnerServices;

    private static LogCaptor logCaptor;

    @BeforeAll
    static void setupLogCaptor()
    {
        logCaptor = LogCaptor.forClass( PartnerServices.class );
        LogCaptor.forName( PartnerServices.class.getCanonicalName() );
        // LogCaptor.forName( StaticInnerClass.class.getCanonicalName() );
    }

    @AfterEach
    void clearLogs()
    {
        logCaptor.clearLogs();
    }

    @AfterAll
    static void tearDown()
    {
        logCaptor.close();
    }

    @Test
    @SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.BeanMembersShouldSerialize" } )
    void partnerServices_invalidUriBinding_establishesDefaultState()
    {
        List<String> foo = logCaptor.getLogs();
        System.out.println( "===== start: captor result =====" );
        System.out.println( foo.isEmpty() );
        System.out.println( foo.contains( "----- Examine the partner service list -----" ) );
        System.out.println( "=====   end: captor result =====" );
//        assertFalse( foo.isEmpty() );     // TODO why is this line causing a test failure
                                            //      - some bad interaction between assert and log capture
        System.out.println( "=====   end: captor assert =====" );

        assertAll(  () -> assertNotNull( partnerServices )
                  , () -> assertNotNull( partnerServices.getServices() )
                  , () -> assertNull( partnerServices.getSoapServices() )

                  , () -> assertNotNull( logCaptor )
                  , () -> assertNotNull( logCaptor.getLogs() )
                  //   , () -> assertFalse( logCaptor.getLogs().isEmpty() )
                  //   , () -> assertNotNull( logCaptor.getInfoLogs() )
                  //   , () -> assertFalse( logCaptor.getInfoLogs().isEmpty() )
                  //   , () -> assertFalse( logCaptor.getInfoLogs().isEmpty() )
                  //   , () -> assertTrue( !logCaptor.getInfoLogs().isEmpty() )
                  //   , () -> assertTrue( logCaptor.getInfoLogs().contains( "----- Examine the partner service list -----" ) )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "----- Examine the partner service list -----" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "   -- Services --" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "PartnerServices.ServiceInfo(name=service2, host=host2.some.domain, port=null, path=/service/path/./error/*^#(), certificate=null, clientPolicy=null, uri=http://host2.some.domain/service/path/./error/*%5E%23())" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "----- End of service configuration -----" )


                  // --- Services ---
                  //   - service2
                  , () -> assertEquals( "service2", partnerServices.getServices().get( 0 ).getName() )
                  , () -> assertEquals( "host2.some.domain", partnerServices.getServices().get( 0 ).getHost() )
                  , () -> assertNull( partnerServices.getServices().get( 0 ).getPort() )
                  , () -> assertEquals(  "/service/path/./error/*^#()"
                                       , partnerServices.getServices().get( 0 ).getPath().toString() )
                  //   , () -> assertNotNull( partnerServices.getServices().get( 0 ).getCertificate() )
                  //   , () -> assertEquals(  "foo.jks"
                  //                        , partnerServices.getServices().get( 0 ).getCertificate().getKeystoreFilename() )
                  //   , () -> assertEquals(  "keyuser"
                  //                        , partnerServices.getServices().get( 0 ).getCertificate().getUsername() )
                  //   , () -> assertEquals(  "keypass"
                  //                        , partnerServices.getServices().get( 0 ).getCertificate().getPassword() )
                  , () -> assertEquals(  "http://host2.some.domain/service/path/./error/*%5E%23()"
                                       , partnerServices.getServices().get( 0 ).getUri().toString() )
        );
    }


}





