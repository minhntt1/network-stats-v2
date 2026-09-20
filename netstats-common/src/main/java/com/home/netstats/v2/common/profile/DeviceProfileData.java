package com.home.netstats.v2.common.profile;

/**
 * 
 * DeviceProfileData abstraction for all device profile data.
 */
public interface DeviceProfileData {

    /**
     * Convert current profile data to json 
     * to persist to the database.
     * 
     * @return json string representation of profile
     */
    String toJson();
}
