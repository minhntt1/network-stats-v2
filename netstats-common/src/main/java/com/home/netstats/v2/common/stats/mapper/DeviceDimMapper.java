package com.home.netstats.v2.common.stats.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.home.netstats.v2.common.stats.DeviceDim;

/**
 * 
 * DeviceDimMapper defines methods to query, update data in {@code device_dim}
 * 
 * @see DeviceDim
 */
@Mapper 
public interface DeviceDimMapper {

    /**
     * Count all the number of records in device_dim 
     * (for calculating pagination)
     * 
     * @return the number of records
     */
    Long countAll();

    /**
     * 
     * Get all the records in device_dim with pagination 
     * 
     * @param limit
     * @param offset
     * @return
     */
    List<DeviceDim> findAllPagination(@Param("limit") Long limit, @Param("offset") Long offset);
} 

