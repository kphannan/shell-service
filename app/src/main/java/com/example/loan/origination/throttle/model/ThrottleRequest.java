package com.example.loan.origination.throttle.model;

/**
 * Interface of Domain Object.
 */
public interface ThrottleRequest
{
    int getAppType();

    String getAppId();

    int getReengagementInd();

    int getOfferMatch();

    int getOfferCode();

    String getPrefilledAppId();

    boolean isSecondaryFlow();

    String getChannelType();

    void setAppType( int appType );

    void setAppId( String appId );

    void setReengagementInd( int reengagementInd );

    void setOfferMatch( int offerMatch );

    void setOfferCode( int offerCode );

    void setPrefilledAppId( String prefilledAppId );

    void setSecondaryFlow( boolean secondaryFlow );

    void setChannelType( String channelType );

    @Override
    boolean equals( Object o );
}
