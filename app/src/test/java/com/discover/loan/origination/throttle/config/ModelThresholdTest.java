package com.discover.loan.origination.throttle.config;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Unit tests for ModelThreshold class.
 */
class ModelThresholdTest
{

    @Test
    void modelConfig_constructor_establishesDefaultState()
    {
        final ModelThreshold testModel = new ModelThreshold();
        assertAll(  () -> assertNotNull( testModel )
                  , () -> assertNotNull( testModel.getModelThresholds() )
                  , () -> assertTrue( testModel.getModelThresholds().isEmpty() )

        );
    }


}
