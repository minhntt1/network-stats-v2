package com.home.netstats.v2.common.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * DeviceProfile contains profile information of a network device - access point,
 * router, etc. - like host name, user name, password, auth token, etc. to authenticate
 * with them to ingest data
 * 
 * Note that a device profile data class should not include model, fw version inside it,
 * (ex: ModelAxyzFw123, etc.) since all device apis are reverse-engineered and different models / fw MAY 
 * or MAY not share the same structure - no official documents, so
 * version them based on observations instead (ex: Profile1 for VendorAModelAxyzFw123, VendorAModelAxyzFw456)
 */
@Getter 
@Setter 
@NoArgsConstructor 
public class DeviceProfile {

    /**
     * Device profile id
     */
    private Integer id;

    /**
     * Fully-qualified device profile data class name
     * Current profile will be queried by this column and handled 
     * by a respective ingestion workflow 
     */
    private String dataClass;

    /**
     * The actual data of current profile,
     * containing username, password, hostname, etc.
     * 
     * Its structure defined by profile data class
     * 
     * @see DeviceProfileData
     */
    private String data;

    /**
     * Temp data used by workflows during ingestion,
     * like authentication token, etc.
     */
    private String tempData;

}
