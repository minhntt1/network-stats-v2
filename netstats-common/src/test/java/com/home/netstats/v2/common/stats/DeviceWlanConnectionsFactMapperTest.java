package com.home.netstats.v2.common.stats;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.netstats.v2.common.config.AbstractCommonDbTest;
import com.home.netstats.v2.common.stats.mapper.DeviceWlanConnectionsFactMapper;

class DeviceWlanConnectionsFactMapperTest extends AbstractCommonDbTest {

    @Autowired
    private DeviceWlanConnectionsFactMapper mapper;

    private DeviceDim deviceKey1() {
        DeviceDim device = new DeviceDim();
        device.setDeviceKey(1);
        return device;
    }

    @Test
    void countByDeviceKey_returnsConnectionCount() {
        Long count = mapper.countByDeviceKey(deviceKey1());

        // seed data has 3 connection facts for device_key=1
        assertThat(count).isEqualTo(3L);
    }

    @Test
    void filterByDeviceKey_returnsConnectionsForDevice() {
        List<DeviceWlanConnectionsFact> rows = mapper.filterByDeviceKey(deviceKey1(), 0L, 10L);

        assertThat(rows).hasSize(3);
    }

    @Test
    void filterByDeviceKey_emptyForUnknownDevice() {
        DeviceDim unknown = new DeviceDim();
        unknown.setDeviceKey(999);

        List<DeviceWlanConnectionsFact> rows = mapper.filterByDeviceKey(unknown, 0L, 10L);

        assertThat(rows).isEmpty();
    }
}