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
@ExtendWith( SpringExtension.class )
@EnableConfigurationProperties( value = PartnerServices.class )
@TestPropertySource( "classpath:PartnerServiceServiceOnlyTestProperties.properties" )
// @PropertySource("classpath:PartnerServiceTestProperties.yml", factory = YamlPropertySourceFactory::class)
class PartnerServicesServiceOnlyTest
{
    @Autowired
    PartnerServices partnerServices;

    @Test
    void partnerServices_serviceOnlyPropertyBinding_establishesDefaultState()
    {
        assertAll(  () -> assertNotNull( partnerServices )
                  , () -> assertNotNull( partnerServices.getServices() )
                  , () -> assertNull( partnerServices.getSoapServices() )

                  // --- Services ---
                  // //   - service1
                  // , () -> assertEquals( "service1", partnerServices.getServices().get(0).getName() )
                  // , () -> assertEquals( "host1.some.domain", partnerServices.getServices().get(0).getHost() )
                  // , () -> assertEquals( "", partnerServices.getServices().get(0).getPort() )
                  // , () -> assertEquals( "base/path/without/leading/slash", partnerServices.getServices().get(0).getPath() )
                  // , () -> assertNull( partnerServices.getServices().get(0).getCertificate() )
                  // , () -> assertNull( partnerServices.getServices().get(0).getClientPolicy() )
                  // , () -> assertEquals( "http://host1.some.domain/base/path/without/leading/slash", partnerServices.getServices().get(0).getUri() )

                  //   - service2
                  , () -> assertEquals( "service2", partnerServices.getServices().get(0).getName() )
                  , () -> assertEquals( "host2.some.domain", partnerServices.getServices().get(0).getHost() )
                  , () -> assertNull( partnerServices.getServices().get(0).getPort() )
                  , () -> assertEquals( "/service/path", partnerServices.getServices().get(0).getPath() )
                  , () -> assertNotNull( partnerServices.getServices().get(0).getCertificate() )
                  , () -> assertEquals( "foo.jks", partnerServices.getServices().get(0).getCertificate().getKeystoreFilename() )
                  , () -> assertEquals( "keyuser", partnerServices.getServices().get(0).getCertificate().getUsername() )
                  , () -> assertEquals( "keypass", partnerServices.getServices().get(0).getCertificate().getPassword() )
                  , () -> assertEquals( "https://host2.some.domain/service/path", partnerServices.getServices().get(0).getUri() )

                //   //   - service3
                //   , () -> assertEquals( 444, partnerServices.getServices().get(1).getClientPolicy().getConnectTimeout() )
                //   , () -> assertEquals( 111, partnerServices.getServices().get(1).getClientPolicy().getMaxElapsedTime() )
                //   , () -> assertEquals( 222, partnerServices.getServices().get(1).getClientPolicy().getTimeout() )
                //   , () -> assertEquals( 333, partnerServices.getServices().get(1).getClientPolicy().getMaxRetries() )
                // //   , () -> assertEquals( "", partnerServices.getServices().get(1).getClientPolicy().getUseAsyncStrategy() )
                //   , () -> assertEquals( "https://host2.some.domain/service/path", partnerServices.getServices().get(1).getUri() )

                //   , () -> assertEquals( "service3", partnerServices.getServices().get(2).getName() )
                //   , () -> assertEquals( "host3.some.domain", partnerServices.getServices().get(2).getHost() )
                //   , () -> assertNull( partnerServices.getServices().get(2).getPort() )
                //   , () -> assertNull( partnerServices.getServices().get(2).getPath() )
                //   , () -> assertNull( partnerServices.getServices().get(2).getCertificate() )
                //   , () -> assertNull( partnerServices.getServices().get(2).getClientPolicy() )
                //   , () -> assertEquals( "http://host3.some.domain", partnerServices.getServices().get(2).getUri() )



                //   // --- Services ---
                //   , () -> assertNotNull( partnerServices.getSoapServices() )
                //   //   - service2
                //   , () -> assertEquals( "service2", partnerServices.getSoapServices().get(1).getName() )
                //   , () -> assertEquals( "host2", partnerServices.getSoapServices().get(1).getHost() )
                //   , () -> assertEquals( "9876", partnerServices.getSoapServices().get(1).getPort() )
                //   , () -> assertEquals( "/secure/soap", partnerServices.getSoapServices().get(1).getPath() )
                //   , () -> assertNotNull( partnerServices.getSoapServices().get(1).getCertificate() )
                //   , () -> assertEquals( "soap.jks", partnerServices.getSoapServices().get(1).getCertificate().getKeystoreFilename() )
                //   , () -> assertEquals( "soapuser", partnerServices.getSoapServices().get(1).getCertificate().getUsername() )
                //   , () -> assertEquals( "soappass", partnerServices.getSoapServices().get(1).getCertificate().getPassword() )

                //   , () -> assertEquals( 111, partnerServices.getSoapServices().get(1).getClientPolicy().getConnectTimeout() )
                //   , () -> assertEquals( 444, partnerServices.getSoapServices().get(1).getClientPolicy().getMaxElapsedTime() )
                //   , () -> assertEquals( 333, partnerServices.getSoapServices().get(1).getClientPolicy().getTimeout() )
                //   , () -> assertEquals( 222, partnerServices.getSoapServices().get(1).getClientPolicy().getMaxRetries() )
                // //   , () -> assertEquals( "", partnerServices.getSoapServices().get(1).getClientPolicy().getUseAsyncStrategy() )
                //   , () -> assertEquals( "https://host2:9876/secure/soap", partnerServices.getSoapServices().get(1).getUri() )

        );
    }


}





