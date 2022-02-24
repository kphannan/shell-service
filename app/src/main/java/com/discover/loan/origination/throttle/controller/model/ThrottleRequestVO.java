package com.discover.loan.origination.throttle.controller.model;


import com.discover.loan.origination.throttle.model.ThrottleRequest;
import lombok.Data;

/**
 * Request object for /eligible API.
 */
@Data
//@Builder
public class ThrottleRequestVO implements ThrottleRequest
{
    private int     appType;         //:(int,required)1,2,3,5....
    private String  appId;           //:(string,required)
    private int     reengagementInd; //( int, required )
    private int     offerMatch;      //( int, required )
    private int     offerCode;       //( int, required )
    private String  prefilledAppId;  //( string )
    private boolean secondaryFlow;   //( boolean )
    private String  channelType;     //( string, required )
}
