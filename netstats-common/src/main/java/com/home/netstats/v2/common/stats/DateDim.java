package com.home.netstats.v2.common.stats;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * DateDim defines a normalized date. A date represents
 * an event that occurs at. All date and time in the database
 * represent events happening in UTC time
 */
@Getter 
@NoArgsConstructor 
public class DateDim {
    /**
     * The normalized index of a date
     */
    private Integer dateKey;

    /**
     * A raw date with yyyy, MM, dd only, no timezone - maps to
     * MySQL {@code DATE} type
     */
    private LocalDate date;


}
