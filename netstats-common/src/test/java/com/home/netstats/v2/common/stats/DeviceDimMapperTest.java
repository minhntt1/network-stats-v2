package com.home.netstats.v2.common.stats;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.netstats.v2.common.config.AbstractCommonDbTest;
import com.home.netstats.v2.common.stats.mapper.DeviceDimMapper;

class DeviceDimMapperTest extends AbstractCommonDbTest {

    @Autowired
    private DeviceDimMapper mapper;

    @Test
    void countAll_returnsSeededCount() {
        Long count = mapper.countAll();
        assertThat(count).isEqualTo(3L);
    }

    @Test
    void findAllPagination_returnsPage() {
        List<DeviceDim> page = mapper.findAllPagination(2L, 0L);

        assertThat(page).hasSize(2);
        assertThat(page.get(0).getDeviceKey()).isEqualTo(1);
        assertThat(page.get(0).getDeviceName()).isEqualTo("phone-1");
    }

    @Test
    void findAllPagination_skipsOffset() {
        List<DeviceDim> page = mapper.findAllPagination(10L, 2L);

        // 3 seeded devices, skip first 2 => last one remains
        assertThat(page).hasSize(1);
        assertThat(page.get(0).getDeviceKey()).isEqualTo(3);
    }
}