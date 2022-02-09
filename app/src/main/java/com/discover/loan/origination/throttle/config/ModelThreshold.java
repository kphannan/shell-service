package com.discover.loan.origination.throttle.config;

import java.util.ArrayList;
import java.util.List;
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
@ConfigurationProperties( prefix = "throttle.model" )
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


    @PostConstruct
    @GeneratedIgnoreCoverage
    @SuppressWarnings( "PMD.UnusedPrivateMethod" )  // Called automatically by the runtime system
    private void show()
    {
        if ( null != modelThresholds )
        {
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
        private String           segmentName;
        @Setter( AccessLevel.MODULE )
        private SegmentationKind segmentKind;
        @Setter( AccessLevel.MODULE )
        private double           targetPercent;
    }
}

