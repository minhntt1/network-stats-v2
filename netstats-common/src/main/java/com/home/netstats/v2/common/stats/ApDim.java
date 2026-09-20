package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * ApDim defines an access point record specified by its physical MAC address
 * and its name - set by users. The MAC address here refers to the MAC of its 
 * interface used to connect to a router or switch, 
 * rather than the BSSIDs it broadcasts 
 */
@Getter 
@NoArgsConstructor 
public class ApDim {
    /**
     * The normalized index of an AP
     */
    private Integer apKey;

    /**
     * The mac address of an AP. 
     * Use a 64-bit signed long to represent a 48-bit-long MAC address
     */
    private Long apMac;

    /**
     * The name of access point, should be 255-character
     */
    private String apName;
}
