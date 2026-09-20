package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * DeviceWlanConnectionsFact contains information about a connection / disconnection
 * of a client to / from the network. The time of event measured based on when workers
 * pull data from access points - if real connect/disconnect time not provided by access points.
 * 
 * The information include date, time (second-precision), device information, device ip, 
 * ap, interface client connected to, and the manufacturer information of the client and interface 
 */
@Getter 
@NoArgsConstructor 
public class DeviceWlanConnectionsFact {

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
     * Define a reference to a record in the device/client dimension table
     * @see DeviceDim
     */
    private Integer deviceKey;

    /**
     * Define a reference to a record in the ip dimension table
     * @see IpDim
     */
    private Integer deviceIpKey;

    /**
     * Define a reference to a record in the ap dimension table
     * @see ApDim
     */
    private Integer apKey;

    /**
     * Define a reference to a record in the ap dimension table
     * @see GwIfaceDim
     */
    private Integer ifaceKey;

    /**
     * For client vendor information
     * Define a reference to a record in the vendor dimension table
     * @see VendorDim
     */
    private Integer vendorKey;

    /**
     * For ap vendor information
     * Define a reference to a record in the vendor dimension table
     * @see VendorDim
     */
    private Integer apVendorKey;


    /**
     * For ap vendor information
     * Define a reference to a record in the connection status dimension table
     * @see ConnectionStatusDim
     */
    private Integer cntStatusKey;

    /**
     * Define a timestamp in utc (equivalent to date + time) for sorting
     * events in queries
     */
    private Long eventTimestamp;
}
