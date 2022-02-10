package com.discover.loan.origination.throttle.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;

import com.discover.GeneratedIgnoreCoverage;
import com.discover.loan.origination.throttle.SegmentationKind;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;



/**
 * Configuration.
 */
@ConfigurationProperties( prefix = "throttle" )
@Component
@RefreshScope
@Data
@Log4j2
public class ModelThreshold
{
    private List<TargetThreshold>  modelThresholds;


    ModelThreshold()
    {
        modelThresholds = new ArrayList<>();
    }


    /**
     * Processing of loaded configuration.
     *   - Lookup named strategies, mapping to actual enum instances.
     */
    @PostConstruct
    @GeneratedIgnoreCoverage
    @SuppressWarnings( "PMD.UnusedPrivateMethod" )  // Called automatically by the runtime system
    private void show()
    {
        if ( null != modelThresholds )
        {
            for ( final TargetThreshold item : modelThresholds )
            {
                item.segmentKind = SegmentationKind.valueOf( item.segment
                                                                .trim()
                                                                .toUpperCase( Locale.US ) );
            }

            modelThresholds.forEach( log::info );
        }
    }



    /**
     * Target percent of applications.
     */
    @Data
    public static class TargetThreshold
    {
        @Setter( AccessLevel.MODULE )
        private String           segment;
        @Setter( AccessLevel.MODULE )
        private SegmentationKind segmentKind;
        @Setter( AccessLevel.MODULE )
        private double           targetPercent;
    }
}

