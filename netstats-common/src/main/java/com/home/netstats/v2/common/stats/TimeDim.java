package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 
 * TimeDim defines normalized time in a day - in seconds,
 * starting at 0, ending at 86399. Normally, in DWH, 
 * statistics often for hourly, weekly, etc. data, millisecond-precision
 * is not necessary
 */
@Getter 
@NoArgsConstructor 
public class TimeDim {

    /**
     * Normalized time index
     */
    private Integer timeKey;

    /**
     * Time in a day in seconds - 0 to 86399
     */
    private Integer time;

    
}
