package com.home.netstats.v2.common.profile;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.netstats.v2.common.config.AbstractCommonDbTest;
import com.home.netstats.v2.common.profile.mapper.DeviceProfileMapper;

class DeviceProfileMapperTest extends AbstractCommonDbTest {

    @Autowired
    private DeviceProfileMapper mapper;

    @Test
    void countAllRecords_returnsSeededCount() {
        Long count = mapper.countAllRecords();
        assertThat(count).isEqualTo(4L);
    }

    @Test
    void findByClassProfileData_filtersByDataClass() {
        DeviceProfile probe = new DeviceProfile();
        probe.setDataClass("com.home.netstats.v2.profile.Data");

        List<DeviceProfile> result = mapper.findByClassProfileData(probe);

        assertThat(result).hasSize(2);
        assertThat(result).allSatisfy(p ->
                assertThat(p.getDataClass()).isEqualTo("com.home.netstats.v2.profile.Data"));
        assertThat(result.get(0).getId()).isNotNull();
    }

    @Test
    void findAllWithLimitOffset_paginatesResults() {
        List<DeviceProfile> page = mapper.findAllWithLimitOffset(2L, 1L);

        assertThat(page).hasSize(2);
        assertThat(page).allSatisfy(p -> assertThat(p.getId()).isNotNull());
    }

    @Test
    void createProfile_persistsRow() {
        DeviceProfile profile = new DeviceProfile();
        profile.setDataClass("com.home.netstats.v2.profile.NewData");
        profile.setData("{\"host\":\"ap9\"}");
        profile.setTempData("temp-token");

        mapper.createProfile(profile);

        Long count = mapper.countAllRecords();
        assertThat(count).isEqualTo(5L);
    }

    @Test
    void updateProfile_updatesExistingRow() {
        DeviceProfile created = new DeviceProfile();
        created.setDataClass("com.home.netstats.v2.profile.UpdData");
        created.setData("{\"host\":\"old\"}");
        created.setTempData("old-temp");
        mapper.createProfile(created);

        // id was back-filled by auto-increment
        DeviceProfile probe = new DeviceProfile();
        probe.setDataClass("com.home.netstats.v2.profile.UpdData");
        DeviceProfile persisted = mapper.findByClassProfileData(probe).get(0);

        persisted.setData("{\"host\":\"new\"}");
        persisted.setTempData("new-temp");
        mapper.updateProfile(persisted);

        List<DeviceProfile> after = mapper.findByClassProfileData(probe);
        assertThat(after).hasSize(1);
        assertThat(after.get(0).getData()).isEqualTo("{\"host\":\"new\"}");
        assertThat(after.get(0).getTempData()).isEqualTo("new-temp");
    }

    @Test
    void deleteProfile_removesRowById() {
        DeviceProfile created = new DeviceProfile();
        created.setDataClass("com.home.netstats.v2.profile.DelData");
        created.setData("{}");
        mapper.createProfile(created);

        DeviceProfile probe = new DeviceProfile();
        probe.setDataClass("com.home.netstats.v2.profile.DelData");
        DeviceProfile persisted = mapper.findByClassProfileData(probe).get(0);

        mapper.deleteProfile(persisted);

        Long count = mapper.countAllRecords();
        assertThat(count).isEqualTo(4L);
        assertThat(mapper.findByClassProfileData(probe)).isEmpty();
    }
}