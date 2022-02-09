package com.discover.loan.origination.throttle;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Unit tests for the SegmentationKind enum.
 */
class SegmentationKindTest
{


    @Test
    void segmentationKind_CsSol_hasCorrectFlags()
    {
        final SegmentationKind itemUnderTest = SegmentationKind.CARDMEMBER_SOLICITED;

        assertAll(  () -> assertTrue( itemUnderTest.isCardMember() )
                  , () -> assertTrue( itemUnderTest.isSolicited() )

                  , () -> assertFalse( itemUnderTest.isBroadMarket() )
                  , () -> assertFalse( itemUnderTest.isUnsolicited() )
                  // Protect against both states being set
                //   , () -> assertNotEquals(  itemUnderTest.isCardMember()
                //                           , itemUnderTest.isBroadMarket() )
                //   , () -> assertNotEquals(  itemUnderTest.isSolicited()
                //                           , itemUnderTest.isUnsolicited() )
        );
    }

    @Test
    void segmentationKind_CsUnSol_hasCorrectFlags()
    {
        final SegmentationKind itemUnderTest = SegmentationKind.CARDMEMBER_UNSOLICITED;

        assertAll(  () -> assertTrue( itemUnderTest.isCardMember() )
                  , () -> assertTrue( itemUnderTest.isUnsolicited() )

                  // Protect against both states being set
                  , () -> assertFalse( itemUnderTest.isBroadMarket() )
                  , () -> assertFalse( itemUnderTest.isSolicited() )
        );
    }


    @Test
    void segmentationKind_BmSol_hasCorrectFlags()
    {
        final SegmentationKind itemUnderTest = SegmentationKind.BROAD_MARKET_SOLICITED;

        assertAll(  () -> assertTrue( itemUnderTest.isBroadMarket() )
                  , () -> assertTrue( itemUnderTest.isSolicited() )

                  // Protect against both states being set
                  , () -> assertFalse( itemUnderTest.isCardMember() )
                  , () -> assertFalse( itemUnderTest.isUnsolicited() )
        );
    }

    @Test
    void segmentationKind_BmUnSol_hasCorrectFlags()
    {
        final SegmentationKind itemUnderTest = SegmentationKind.BROAD_MARKET_UNSOLICITED;

        assertAll(  () -> assertTrue( itemUnderTest.isBroadMarket() )
                  , () -> assertTrue( itemUnderTest.isUnsolicited() )

                  // Protect against both states being set
                  , () -> assertFalse( itemUnderTest.isCardMember() )
                  , () -> assertFalse( itemUnderTest.isSolicited() )
        );
    }
}
