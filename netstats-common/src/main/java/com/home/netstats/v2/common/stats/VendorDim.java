package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * VendorDim contains network device manufacturer information
 * using the first 24 bits of a 48-bit mac address.
 * 
 * See: https://www.wireshark.org/tools/oui-lookup.html
 */
@Getter 
@NoArgsConstructor 
public class VendorDim {

    /**
     * Normalized vendor key. Use 32-bit to store. Still enough to store
     * 2^24 vendor identifiers
     */
    private Integer vendorKey;

    /**
     * Vendor prefix - the first 24 bits of a mac address comming 
     * from that vendor
     */
    private Integer vendorPrefix;

    /**
     * The display name of a vendor. 
     * Must contain at most 255 characters UTF-8
     */
    private String vendorName;

    
}
