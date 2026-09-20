package com.home.netstats.v2.common.stats.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.home.netstats.v2.common.stats.DeviceDim;
import com.home.netstats.v2.common.stats.DeviceWlanConnectionsFact;

/**
 * 
 * DeviceWlanConnectionsFactMapper defines methods for <B>only querying</B>
 * device wlan connection data in {@code device_wlan_connections_fact}
 * 
 * @see DeviceWlanConnectionsFact
 * @see DeviceDim
 */
@Mapper 
public interface DeviceWlanConnectionsFactMapper {
    
    /**
     * 
     * Count the number of records filtered by given device 
     * by device_key
     * 
     * @param deviceDim a device object containing non-null device_key
     * @return the number of records
     * 
     * @see DeviceDim
     */
    Long countByDeviceKey(DeviceDim deviceDim);

    /**
     * 
     * Filter a list of connection records by device dim (device_key)
     * 
     * @param deviceDim a device object containing non-null device_key
     * @param limit max records
     * @param offset index of the chunk
     * @return list of connection records
     * 
     * @see DeviceDim
     */
    List<DeviceWlanConnectionsFact> filterByDeviceKey(
        @Param("deviceDim")  DeviceDim deviceDim, 
        @Param("limit") Long limit, 
        @Param ("offset") Long offset);
}
