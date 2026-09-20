-- ============================================================
-- Dummy data for netstats-common integration tests.
-- Loaded after db/schema.sql against the MySQL 8.4 "netstats" DB.
-- ============================================================

-- Device profiles (DeviceProfileMapper)
INSERT INTO device_auth_data_web (data_class, data, temp_data) VALUES
  ('com.home.netstats.v2.profile.Data',        '{"host":"ap1","user":"admin"}',  NULL),
  ('com.home.netstats.v2.profile.Data',        '{"host":"ap2","user":"admin"}',  NULL),
  ('com.home.netstats.v2.profile.OtherData',   '{"host":"rg1","user":"root"}',   NULL),
  ('com.home.netstats.v2.profile.OtherData',   '{"host":"rg2","user":"root"}',   NULL);

-- Devices (DeviceDimMapper)
INSERT INTO device_dim (device_mac, device_name, device_iface_wifi) VALUES
  (1001, 'phone-1', 1),
  (1002, 'laptop-1', 0),
  (1003, 'tablet-1', 1);

-- Dimension rows referenced by the connection-fact joins
INSERT INTO date_dim (date) VALUES ('2026-01-01');
INSERT INTO time_dim (time) VALUES (0);
INSERT INTO ip_dim (ipv4) VALUES (0xA000000); -- ip_key=1
INSERT INTO ip_dim (ipv4) VALUES (0xA000001); -- ip_key=2
INSERT INTO ap_dim (ap_mac, ap_name) VALUES (5001, 'ap-1');   -- ap_key=1
INSERT INTO gw_iface_dim (iface_mac, iface_name, iface_phy_name) VALUES (6001, 'wifi-5g', 'wlan0'); -- iface_key=1
INSERT INTO vendor_dim (vendor_prefix, vendor_name) VALUES (100, 'VendorOne');  -- vendor_key=1
INSERT INTO vendor_dim (vendor_prefix, vendor_name) VALUES (101, 'VendorTwo');  -- vendor_key=2
INSERT INTO connection_status_dim (status) VALUES ('connect');    -- id=1
INSERT INTO connection_status_dim (status) VALUES ('disconnect'); -- id=2

-- Connection facts for device_key=1 (DeviceWlanConnectionsFactMapper)
-- (each row must have a distinct composite PK, which excludes event_timestamp)
INSERT INTO device_wlan_connections_fact
  (date_key, time_key, device_key, device_ip_key, ap_key, iface_key, vendor_key, ap_vendor_key, cnt_status_key, event_timestamp)
VALUES
  (1, 1, 1, 1, 1, 1, 1, 2, 1, 1735689600),
  (1, 1, 1, 2, 1, 1, 1, 2, 1, 1735689700),
  (1, 1, 1, 1, 1, 1, 2, 2, 2, 1735689800);