package com.home.netstats.v2.common.profile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.home.netstats.v2.common.profile.DeviceProfile;

/**
 * 
 * DeviceProfileMapper defines mappers regarding working with device profiles
 * like querying, updating profiles. The mapper works 
 * for table {@code device_auth_data_web}.
 * 
 * <br>
 * 
 * <b>The param names used in this mapper should exactly match with params
 * in its respective XML file</b>
 * 
 * @see DeviceProfile
 */
@Mapper 
public interface DeviceProfileMapper {
    /**
     * Query list of device profiles by its implementation class
     * The implementation class maps to a ingestion workflow class
     * that will handle profiles with that implementation class.
     * 
     * @param profile contains a non-null `dataClass` property for querying
     * @return list of device profiles matching with given class
     */
    List<DeviceProfile> findByClassProfileData(DeviceProfile profile);

    /**
     * Find all profiles information with pagination.
     * 
     * When a method accepts more than one param, it is recommended to 
     * use {@code @Param}.
     * 
     * @param limit the maximum number of returning results
     * @param offset the position of a chunk 
     * @return list of all device profiles in the table
     */
    List<DeviceProfile> findAllWithLimitOffset(@Param("limit") Long limit, @Param("offset") Long offset);

    /**
     * Count the number of all profile records in the table.
     * 
     * @return the number of records
     */
    Long countAllRecords();

    /**
     * 
     * Update a profile information based on
     * input data as a parameter.
     * 
     * @param profile a device profile with a non-null id, otherwise a -1 id lookup will be made
     * @return void
     */
    void updateProfile(DeviceProfile profile);

    /**
     * 
     * Delete a profile by its id.
     * 
     * @param profile a device profile with a non-null id, otherwise a -1 id lookup will be made
     */
    void deleteProfile(DeviceProfile profile);

    /**
     * 
     * Create a device profile.
     * 
     * @param profile profile data
     */
    void createProfile(DeviceProfile profile);

} 
