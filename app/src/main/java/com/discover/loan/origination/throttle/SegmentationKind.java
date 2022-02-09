package com.discover.loan.origination.throttle;


/**
 * Identification of unique segmentation types.
 */
public enum SegmentationKind
{
      CARDMEMBER_SOLICITED(     true,  true  )
    , CARDMEMBER_UNSOLICITED(   true,  false )
    , BROAD_MARKET_SOLICITED(   false, true  )
    , BROAD_MARKET_UNSOLICITED( false, false );


    private final boolean cardMember;
    private final boolean solicited;


    SegmentationKind( final boolean cardMember, final boolean solicited )
    {
        this.cardMember = cardMember;
        this.solicited  = solicited;
    }


    /**
     * Indicator that this segment represents a cardmember.
     *
     * @return true for cardmembers, false otherwise.
     */
    public boolean isCardMember()
    {
        return cardMember;
    }

    /**
     * Indicator that this segment represents a non-cardmember.
     *
     * @return false for cardmembers, true otherwise.
     */
    public boolean isBroadMarket()
    {
        return !cardMember;
    }

    /**
     * Indicates this segment is for a solicited application.
     *
     * @return true for solicited application.
     */
    public boolean isSolicited()
    {
        return solicited;
    }

    /**
     * Indicates this segment represents a non-solicited application.
     *
     * @return true for non-solicited application, true otherwise.
     */
    public boolean isUnsolicited()
    {
        return !isSolicited();
    }
}
