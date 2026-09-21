package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * GwIfaceDim defines an interface information of an access point or router,
 * it could be a wireless, ethernet, etc. interface providing access to clients
 * (ex: A client can connect to a aither 5ghz wifi or 2.4ghz wifi interface)
 * 
 * Each interface has a mac address, interface name (ex: a ssid - set by users),
 * interface physical name - read-only physical name set by the OS (ex: eth0, wlan0), 
 * and interface remark for additional information
 * 
 * Certain devices generate random mac addresses for its wifi interfaces - ex: tplink -
 * so in that case, the mac address will be replaced by ap's mac address
 * 
 * @see ApDim
 */
@Getter 
@NoArgsConstructor 
public class GwIfaceDim {

    /**
     * Normalized interface index in int
     */
    private Integer ifaceKey;

    /**
     * The mac address of the interface
     */
    private Long ifaceMac;

    /**
     * The name of the interface - this is usually set by user.
     * Must contain at most 255 characters
     */
    private String ifaceName;

    /**
     * The physical name of the interface - often read-only - by hardware.
     * Must contain at most 255 characters
     */
    private String ifacePhyName;

    /**
     * Additional information of an interface. 
     * Must contain at most 255 characters
     */
    private String ifaceRemark;
}
