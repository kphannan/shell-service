package com.discover.loan.origination.throttle.config;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.discover.loan.origination.throttle.config.PartnerServices.ServiceInfo;
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
@TestPropertySource( "classpath:PartnerServiceTestProperties.properties" )
// @PropertySource("classpath:PartnerServiceTestProperties.yml", factory = YamlPropertySourceFactory::class)
class PartnerServicesTest
{
    @Autowired
    PartnerServices partnerServices;

    private static LogCaptor logCaptor;

    @BeforeAll
    static void setupLogCaptor()
    {
        logCaptor = LogCaptor.forClass( PartnerServices.class );
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
    void partnerServices_propertyBinding_establishesDefaultState()
    {

        assertAll(  () -> assertNotNull( partnerServices )
                  , () -> assertNotNull( partnerServices.getServices() )

                  // --- Services ---
                  //   - service1
                  , () -> assertEquals( "service1", partnerServices.getServices().get( 0 ).getName() )
                  , () -> assertEquals( "host1.some.domain", partnerServices.getServices().get( 0 ).getHost() )
                  , () -> assertEquals( "", partnerServices.getServices().get( 0 ).getPort() )
                  , () -> assertEquals( "base/path/without/leading/slash", partnerServices.getServices().get( 0 ).getPath() )
                  , () -> assertNull( partnerServices.getServices().get( 0 ).getCertificate() )
                  , () -> assertNull( partnerServices.getServices().get( 0 ).getClientPolicy() )
                  , () -> assertEquals(  "http://host1.some.domain/base/path/without/leading/slash"
                                       , partnerServices.getServices().get( 0 ).getUri().toString() )

                  //   - service2
                  , () -> assertEquals( "service2", partnerServices.getServices().get( 1 ).getName() )
                  , () -> assertEquals( "host2.some.domain", partnerServices.getServices().get( 1 ).getHost() )
                  , () -> assertNull( partnerServices.getServices().get( 1 ).getPort() )
                  , () -> assertEquals( "/service/path", partnerServices.getServices().get( 1 ).getPath() )
                  , () -> assertNotNull( partnerServices.getServices().get( 1 ).getCertificate() )
                  , () -> assertEquals(  "foo.jks"
                                       , partnerServices.getServices().get( 1 ).getCertificate().getKeystoreFilename() )
                  , () -> assertEquals(  "keyuser"
                                       , partnerServices.getServices().get( 1 ).getCertificate().getUsername() )
                  , () -> assertEquals(  "keypass"
                                       , partnerServices.getServices().get( 1 ).getCertificate().getPassword() )
                  , () -> assertEquals(  "https://host2.some.domain/service/path"
                                       , partnerServices.getServices().get( 1 ).getUri().toString() )

                  //   - service3
                  , () -> assertEquals( 444, partnerServices.getServices().get( 1 ).getClientPolicy().getConnectTimeout() )
                  , () -> assertEquals( 111, partnerServices.getServices().get( 1 ).getClientPolicy().getMaxElapsedTime() )
                  , () -> assertEquals( 222, partnerServices.getServices().get( 1 ).getClientPolicy().getTimeout() )
                  , () -> assertEquals( 333, partnerServices.getServices().get( 1 ).getClientPolicy().getMaxRetries() )
                  , () -> assertEquals(  "https://host2.some.domain/service/path"
                                       , partnerServices.getServices().get( 1 ).getUri().toString() )

                  , () -> assertEquals( "service3", partnerServices.getServices().get( 2 ).getName() )
                  , () -> assertEquals( "host3.some.domain", partnerServices.getServices().get( 2 ).getHost() )
                  , () -> assertNull( partnerServices.getServices().get( 2 ).getPort() )
                  , () -> assertNull( partnerServices.getServices().get( 2 ).getPath() )
                  , () -> assertNull( partnerServices.getServices().get( 2 ).getCertificate() )
                  , () -> assertNull( partnerServices.getServices().get( 2 ).getClientPolicy() )
                  , () -> assertEquals(  "http://host3.some.domain"
                                       , partnerServices.getServices().get( 2 ).getUri().toString() )



                  // --- Services ---
                  , () -> assertNotNull( partnerServices.getSoapServices() )
                  //   - service2
                  , () -> assertEquals( "service2", partnerServices.getSoapServices().get( 1 ).getName() )
                  , () -> assertEquals( "host2", partnerServices.getSoapServices().get( 1 ).getHost() )
                  , () -> assertEquals( "9876", partnerServices.getSoapServices().get( 1 ).getPort() )
                  , () -> assertEquals( "/secure/soap", partnerServices.getSoapServices().get( 1 ).getPath() )
                  , () -> assertNotNull( partnerServices.getSoapServices().get( 1 ).getCertificate() )
                  , () -> assertEquals(  "soap.jks"
                                       , partnerServices.getSoapServices().get( 1 ).getCertificate().getKeystoreFilename() )
                  , () -> assertEquals(  "soapuser"
                                       , partnerServices.getSoapServices().get( 1 ).getCertificate().getUsername() )
                  , () -> assertEquals(  "soappass"
                                       , partnerServices.getSoapServices().get( 1 ).getCertificate().getPassword() )

                  , () -> assertEquals( 111, partnerServices.getSoapServices().get( 1 ).getClientPolicy().getConnectTimeout() )
                  , () -> assertEquals( 444, partnerServices.getSoapServices().get( 1 ).getClientPolicy().getMaxElapsedTime() )
                  , () -> assertEquals( 333, partnerServices.getSoapServices().get( 1 ).getClientPolicy().getTimeout() )
                  , () -> assertEquals( 222, partnerServices.getSoapServices().get( 1 ).getClientPolicy().getMaxRetries() )
                  , () -> assertEquals(  "https://host2:9876/secure/soap"
                                       , partnerServices.getSoapServices().get( 1 ).getUri().toString() )

                  //   , () -> assertThat( logCaptor.getInfoLogs() ).containsExactly( "foo" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "----- Examine the partner service list -----" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "   -- Services --" )

                  //    , () -> assertThat( logCaptor.getInfoLogs() ).contains( "PartnerServices.ServiceInfo(name=service1, host=host1.some.domain, port=null, path=base/path/without/leading/slash, certificate=null, clientPolicy=null, uri=http://host1.some.domain/base/path/without/leading/slash)" )
                  //    , () -> assertThat( logCaptor.getInfoLogs() ).contains( "PartnerServices.ServiceInfo(name=service2, host=host2.some.domain, port=null, path=null, certificate=PartnerServices.CertificateInfo(keystoreFilename=foo.jks, username=keyUser, password=keyPass), clientPolicy=null, uri=https://host2.some.domain)" )
                  //    , () -> assertThat( logCaptor.getInfoLogs() ).contains( "PartnerServices.ServiceInfo(name=service3, host=host3.some.domain, port=null, path=null, certificate=null, clientPolicy=null, uri=http://host3.some.domain)" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "   -- SOAP Services --" )
                  //   , () -> assertThat( logCaptor.getInfoLogs() ).contains( "----- End of service configuration -----" )
        );
    }

    // TODO temporary examples of log output to verify
    // : ----- Examine the parter service list -----
    // :    -- Services --
    // : PartnerServices.ServiceInfo(name=service1, host=host1.some.domain, port=null, path=base/path/without/leading/slash, certificate=null, clientPolicy=null, uri=http://host1.some.domain/base/path/without/leading/slash)
    // : PartnerServices.ServiceInfo(name=service2, host=host2.some.domain, port=null, path=null, certificate=PartnerServices.CertificateInfo(keystoreFilename=foo.jks, username=keyUser, password=keyPass), clientPolicy=null, uri=https://host2.some.domain)
    // : PartnerServices.ServiceInfo(name=service3, host=host3.some.domain, port=null, path=null, certificate=null, clientPolicy=null, uri=http://host3.some.domain)
    // :    -- SOAP Services --
    // : PartnerServices.ServiceInfo(name=soapService1, host=host1.some.domain, port=null, path=null, certificate=null, clientPolicy=null, uri=http://host1.some.domain)
    // : PartnerServices.ServiceInfo(name=soapService2, host=host2.some.domain, port=1234, path=/base/path, certificate=null, clientPolicy=PartnerServices.ClientPolicy(connectTimeout=100, maxRetries=5, timeout=200, maxElapsedTime=900, useAsyncStrategy=false), uri=http://host2.some.domain:1234/base/path)
    // : ----- End of service configuration -----





    @Test
    void partnerServices_lookupService_returnsServiceInfo()
    {
        final ServiceInfo info = PartnerServices.getServiceInfo( "service2" );

        assertAll(  () -> assertNotNull( info ) );
    }


    @Test
    void partnerServices_lookupSoapService_returnsServiceInfo()
    {
        final ServiceInfo info = PartnerServices.getSoapServiceInfo( "service1" );

        assertAll(  () -> assertNotNull( info ) );
    }
}





