package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * IpDim represents normalized ipv4 and ipv6 addresses in the database
 * Known limitation: normalized index is int - caps at 4B addresses - while 
 * the number of ipv6 address easily exceeds 2^32 - at most 2^128 - does not fit
 * in any int or long. Even MySQL bigint only 64-bit. 
 * 
 * See: https://dev.mysql.com/doc/refman/8.4/en/integer-types.html 
 * 
 * Now it works but a schema change is necessary 
 */
@Getter 
@NoArgsConstructor 
public class IpDim {
    
    /**
     * The index that any ipv4/ipv6 addresses normalized to
     */
    private Integer ipKey;
    
    /**
     * Represents an IPv4 using a signed 32-bit int - perfectly valid
     */
    private Integer ipv4;

    /**
     * Represents an IPv6 using a signed 64-bit long - known limitation
     */
    private Long ipv6;

    
}
