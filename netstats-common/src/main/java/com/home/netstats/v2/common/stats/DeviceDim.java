package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * DeviceDim contains a client information using the network, 
 * specified by its mac address, name and whether it connects 
 * to a wifi interface or not
 */
@Getter 
@Setter 
@NoArgsConstructor 
public class DeviceDim {
    
    /**
     * The normalized index of device
     */
    private Integer deviceKey;

    /**
     * The mac address of device - not modifiable by users, but can be
     * randomly generated to protect users' privacy
     */
    private Long deviceMac;

    /**
     * The name of device - usually set by users. 
     * Must contain at most 255 characters
     */
    private String deviceName;

    /**
     * Indicate the device connects to the network using wifi or not
     * Can be 0 or 1
     */
    private Short deviceIfaceWifi;
}
