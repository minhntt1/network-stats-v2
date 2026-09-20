package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * DeviceTrafficByHourFact contains hourly traffic information of a client,
 * including download and upload traffic in bytes 
 */
@Getter 
@NoArgsConstructor 
public class DeviceTrafficByHourFact {
    /**
     * Define a reference to a record in the date dimension table
     * @see DateDim
     */
    private Integer dateKey;
    /**
     * Define a reference to a record in the access point dimension table
     * @see TimeDim
     */
    private Integer timeKey;
    /**
     * Define a reference to a record in the device/client dimension table
     * @see DeviceDim
     */
    private Integer deviceKey;

    /**
     * Total transmission bytes of a client within an hour
     */
    private Integer transmissionBytes;
}
