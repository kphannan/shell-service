package com.discover.loan.origination.throttle.service.model;

import com.discover.loan.origination.throttle.controller.model.ThrottleRequestVO;
import com.discover.loan.origination.throttle.model.ThrottleRequest;
import lombok.Data;


@Data
public class ThrottleRequestEO implements ThrottleRequest
{
    private final ThrottleRequest adaptee;

    public ThrottleRequestEO( final ThrottleRequestVO vo )
    {
        adaptee = vo;
    }


    @Override
    public int getAppType()
    {
        return adaptee.getAppType();
    }

    @Override
    public String getAppId()
    {
        return adaptee.getAppId();
    }

    @Override
    public int getReengagementInd()
    {
        return adaptee.getReengagementInd();
    }

    @Override
    public int getOfferMatch()
    {
        return adaptee.getOfferMatch();
    }

    @Override
    public int getOfferCode()
    {
        return adaptee.getOfferCode();
    }

    @Override
    public String getPrefilledAppId()
    {
        return adaptee.getPrefilledAppId();
    }

    @Override
    public boolean isSecondaryFlow()
    {
        return adaptee.isSecondaryFlow();
    }

    @Override
    public String getChannelType()
    {
        return adaptee.getChannelType();
    }

    @Override
    public void setAppType( int appType )
    {
        adaptee.setAppType( appType );
    }

    @Override
    public void setAppId( String appId )
    {
        adaptee.setAppId( appId );
    }

    @Override
    public void setReengagementInd( int reengagementInd )
    {
        adaptee.setReengagementInd( reengagementInd );
    }

    @Override
    public void setOfferMatch( int offerMatch )
    {
        adaptee.setOfferMatch( offerMatch );
    }

    @Override
    public void setOfferCode( int offerCode )
    {
        adaptee.setOfferCode( offerCode );
    }

    @Override
    public void setPrefilledAppId( String prefilledAppId )
    {
        adaptee.setPrefilledAppId( prefilledAppId );
    }

    @Override
    public void setSecondaryFlow( boolean secondaryFlow )
    {
        adaptee.setSecondaryFlow( secondaryFlow );
    }

    @Override
    public void setChannelType( String channelType )
    {
        adaptee.setChannelType( channelType );
    }

//    @Override
//    public boolean canEqual( Object other )
//    {
//        return adaptee.canEqual( other );
//    }
}
