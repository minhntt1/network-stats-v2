package com.home.netstats.v2.common.stats;

import lombok.Getter;

/**
 * 
 * ApRebootCntPerWeekFact contains information about the number of reboot events
 * of each access point weekly. 
 * 
 * The week here follows ISO week date - starting at monday to sunday -
 * and based on UTC time via MySQL weekday on a UTC date. 
 * 
 * Each week represented by a week start date, calculated by {@code date - interval weekday(date) day}
 * 
 * Possibly duplicated with Connection lost daily and should be deprecated
 * 
 * @see ApConnectionLostDailyFact
 */
@Getter 
@Deprecated 
public class ApRebootCntPerWeekFact {

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
     * The number of reboot events weekly
     */
    private Integer countReboot;
}
