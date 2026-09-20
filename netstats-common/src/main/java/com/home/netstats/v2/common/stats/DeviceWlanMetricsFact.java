package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * DeviceWlanMetricsFact contains metrics information of a client at a moment 
 * defined by date, time (second-precision). The metrics could be snr, etc.
 */
@Getter 
@NoArgsConstructor 
public class DeviceWlanMetricsFact {

    /**
     * Define a reference to a record in the date dimension table
     * @see DateDim
     */
    private Integer dateKey;

    /**
     * Define a reference to a record in the time dimension table
     * @see TimeDim
     */
    private Integer timeKey;
 
    /**
     * Define a reference to a record in the time dimension table
     * @see DeviceDim
     */
    private Integer deviceKey;

    /**
     * Define a metric of a client collected from access points
     */
    private Integer snr;
}
