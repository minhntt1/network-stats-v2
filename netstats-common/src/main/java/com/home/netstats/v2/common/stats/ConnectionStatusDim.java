package com.home.netstats.v2.common.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * ConnectionStatusDim stores information related to connection state of clients
 * Possible values: connect, disconnect
 */
@Getter 
@NoArgsConstructor 
public class ConnectionStatusDim {

    /**
     * Normalized id for connection status
     */
    private Integer id;

    /**
     * Connection status indicator. Possible values: connect, disconnect
     * Must contain at most 20 characters
     */
    private String status;
}
