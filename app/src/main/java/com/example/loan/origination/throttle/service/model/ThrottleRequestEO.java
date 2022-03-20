package com.example.loan.origination.throttle.service.model;

import com.example.loan.origination.throttle.controller.model.ThrottleRequestVO;
import com.example.loan.origination.throttle.model.ThrottleRequest;
import lombok.Data;

/**
 * Business layer domain object.
 */
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
    public void setAppType( final int appType )
    {
        adaptee.setAppType( appType );
    }

    @Override
    public void setAppId( final String appId )
    {
        adaptee.setAppId( appId );
    }

    @Override
    public void setReengagementInd( final int reengagementInd )
    {
        adaptee.setReengagementInd( reengagementInd );
    }

    @Override
    public void setOfferMatch( final int offerMatch )
    {
        adaptee.setOfferMatch( offerMatch );
    }

    @Override
    public void setOfferCode( final int offerCode )
    {
        adaptee.setOfferCode( offerCode );
    }

    @Override
    public void setPrefilledAppId( final String prefilledAppId )
    {
        adaptee.setPrefilledAppId( prefilledAppId );
    }

    @Override
    public void setSecondaryFlow( final boolean secondaryFlow )
    {
        adaptee.setSecondaryFlow( secondaryFlow );
    }

    @Override
    public void setChannelType( final String channelType )
    {
        adaptee.setChannelType( channelType );
    }

}
