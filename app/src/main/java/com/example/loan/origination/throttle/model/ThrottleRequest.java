package com.example.loan.origination.throttle.model;

/**
 * Interface of Domain Object.
 */
public interface ThrottleRequest
{
    int getAppType();

    void setAppType( int appType );


    String getAppId();

    void setAppId( String appId );


    int getReengagementInd();

    void setReengagementInd( int reengagementInd );


    int getOfferMatch();

    void setOfferMatch( int offerMatch );


    int getOfferCode();

    void setOfferCode( int offerCode );


    String getPrefilledAppId();

    void setPrefilledAppId( String prefilledAppId );


    boolean isSecondaryFlow();

    void setSecondaryFlow( boolean secondaryFlow );


    String getChannelType();

    void setChannelType( String channelType );


    @Override
    boolean equals( Object o );
}
