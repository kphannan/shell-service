package com.discover.loan.origination.throttle.config;

import java.net.URISyntaxException;
import java.util.List;
import javax.annotation.PostConstruct;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;





/**
 * This is the partner service configuration.
 *
 */
@ConfigurationProperties( prefix = "partner-services" )
@Component
@RefreshScope
@Data
@Log4j2
public class PartnerServices
{
    private String                foo;
    private String                channelId;        //< default channel ID
    private String                applicationId;    //< default application id
    private List<ServiceInfo>     services;
    private List<ServiceInfo>     soapServices;


    @PostConstruct
    private void init() // NOPMD
    {
        // Build out the transient elements....
        if ( null != soapServices )
        {
            soapServices.forEach( this::buildUri );
        }

        if ( null != services )
        {
            services.forEach( this::buildUri );
        }

        logPartnerServices();
    }


    /**
     * Construct a URI string from the parts specified in the serviceInfo.
     *
     * @param serviceInfo elements of a URI (host, port, path) and optional credentials
     */
    private void buildUri( final ServiceInfo serviceInfo )
    {
        final URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme( serviceInfo.certificate == null ? "http" : "https" )
                  .setHost( serviceInfo.host )
                  .setPath( serviceInfo.path );

        try
        {
            uriBuilder.setPort( Integer.valueOf( serviceInfo.port ) );
        }
        catch ( NumberFormatException nfe )
        {
            uriBuilder.setPort( -1 );
        }

        try
        {
            serviceInfo.uri = uriBuilder.build().toString();
        }
        catch ( URISyntaxException e )
        {
            log.error( e );
        }
    }

    /**
     * Address elements for connection to a partner REST service.
     */
    @Data
    @Setter( AccessLevel.MODULE )
    public static class ServiceInfo
    {
        @Setter( AccessLevel.MODULE )
        private String          name;           //< Name of the service
        @Setter( AccessLevel.MODULE )
        private String          host;           //< host only of the URL
        @Setter( AccessLevel.MODULE )
        private String          port;           //< port number
        @Setter( AccessLevel.MODULE )
        private String          path;           //< optional - base path, must begin with a '/'

        @Setter( AccessLevel.MODULE )
        private CertificateInfo certificate;    //< Optional certificate keystore

        @Setter( AccessLevel.MODULE )
        private ClientPolicy    clientPolicy;   //< Optional policy details (timeouts, retries, etc.)

        @Setter( AccessLevel.NONE )
        private String uri;                     //< Constructed URI from host, port, certificate details
    }


    /**
     * Security credentials.
     */
    @Data
    @Setter( AccessLevel.MODULE )
    public static class CertificateInfo
    {
        private String    keystoreFilename;     //< filename (no path) of the keystore
        private String    username;             //< keystore credentials
        private String    password;             // < keystore credentials
    }

    /**
     * Partner connection error handling configuration.
     */
    @Data
    @Setter( AccessLevel.MODULE )
    public static class ClientPolicy
    {
        private int      connectTimeout;    //< max milliseconds to establish a connection
        private int      maxRetries;        //< count of retries
        private int      timeout;           //< timeout in ms; zero (0) results in no timeout
        private int      maxElapsedTime;    //< maximum elapsed time from first attempt in ms.
        private boolean  useAsyncStrategy;  //< if true, calls are async otherwise synchronous
    }


    private void logPartnerServices()
    {
        log.info( "----- Examine the parter service list -----" );

        if ( null != services )
        {
            log.info( "   -- Services --" );
            services.forEach( log::info );
        }

        if ( null != soapServices )
        {
            log.info( "   -- SOAP Services --" );
            soapServices.forEach( log::info );
        }

        log.info( "----- End of service configuration -----" );
    }

}


