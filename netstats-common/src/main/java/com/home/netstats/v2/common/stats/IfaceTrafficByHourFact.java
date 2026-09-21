package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * IfaceTrafficByHourFact represents hourly traffic in bytes 
 * of a gateway interface
 * using date, time and interface information
 */
@Getter 
@NoArgsConstructor 
public class IfaceTrafficByHourFact {

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
     * Define a reference to a record in the gateway interface dimension table
     * @see GwIfaceDim
     */
    private Integer ifaceKey;

    /**
     * 
     * Transmitted data in bytes
     */
    private Long transmissionBytes;
}
