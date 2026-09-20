package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * ApConnectionLostDailyFact represents the number of connection loss events of
 * an access point specified by its ip address in a date. The IP address used
 * here is the address the AP used to connected to a switch/router 
 * to provide network for its client
 * 
 * An event considered connection loss when the ap is active then becomes offline
 */
@Getter 
@NoArgsConstructor 
public class ApConnectionLostDailyFact {
    /**
     * Define a reference to a record in the date dimension table
     * @see DateDim
     */
    private Integer dateKey;

    /**
     * Define a reference to a record in the access point dimension table
     * @see TimeDim
     */
    private Integer apKey;

    /**
     * Define a reference to a record in the access point dimension table
     * @see IpDim
     */
    private Integer ipKey;

    /**
     * The number of connection loss events per day
     */
    private Integer lostCnt;
}
