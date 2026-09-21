-- ============================================================
-- Schema for netstats-common integration tests.
-- Mirrors the tables from the liquibase prod baseline:
--   local-infra/database/prod/mysql/network_statistics/sql/v00000__baseline.sql
-- Only the tables that the current common mappers reference are
-- re-created here so the container stays small/fast.
-- Configured against the MySQL 8.4 "netstats" database.
-- ============================================================

-- ---- device_auth_data_web (DeviceProfileMapper) ----
CREATE TABLE device_auth_data_web (
  data_class varchar(255) DEFAULT NULL,
  data longtext,
  temp_data longtext,
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  KEY device_auth_web_data_class_IDX (data_class)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ---- device_dim (DeviceDimMapper) ----
CREATE TABLE device_dim (
  device_key int NOT NULL AUTO_INCREMENT,
  device_mac bigint NOT NULL,
  device_name varchar(255) DEFAULT NULL,
  device_iface_wifi tinyint NOT NULL,
  PRIMARY KEY (device_key),
  UNIQUE KEY search (device_iface_wifi, device_mac, device_name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ---- dimensions used by device_wlan_connections_fact joins ----

CREATE TABLE date_dim (
  date_key int NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  PRIMARY KEY (date_key),
  UNIQUE KEY search (date)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE time_dim (
  time_key int NOT NULL AUTO_INCREMENT,
  time int NOT NULL,
  PRIMARY KEY (time_key),
  UNIQUE KEY search (time)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ip_dim (
  ip_key int NOT NULL AUTO_INCREMENT,
  ipv4 int DEFAULT NULL,
  ipv6 bigint DEFAULT NULL,
  PRIMARY KEY (ip_key),
  UNIQUE KEY search4 (ipv4),
  UNIQUE KEY search6 (ipv6)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ap_dim (
  ap_key int NOT NULL AUTO_INCREMENT,
  ap_mac bigint NOT NULL,
  ap_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (ap_key),
  UNIQUE KEY search (ap_mac, ap_name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE gw_iface_dim (
  iface_key int NOT NULL AUTO_INCREMENT,
  iface_mac bigint NOT NULL,
  iface_name varchar(255) DEFAULT NULL,
  iface_phy_name varchar(255) DEFAULT NULL,
  iface_remark varchar(255) DEFAULT NULL,
  PRIMARY KEY (iface_key),
  UNIQUE KEY search (iface_mac, iface_phy_name, iface_name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE vendor_dim (
  vendor_key int NOT NULL AUTO_INCREMENT,
  vendor_prefix int NOT NULL,
  vendor_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (vendor_key),
  UNIQUE KEY search (vendor_prefix)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE connection_status_dim (
  status varchar(20) NOT NULL,
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ---- device_wlan_connections_fact (DeviceWlanConnectionsFactMapper) ----
CREATE TABLE device_wlan_connections_fact (
  date_key int NOT NULL,
  time_key int NOT NULL,
  device_key int NOT NULL,
  device_ip_key int NOT NULL,
  ap_key int NOT NULL,
  iface_key int NOT NULL,
  vendor_key int NOT NULL,
  ap_vendor_key int NOT NULL,
  cnt_status_key int NOT NULL,
  event_timestamp bigint DEFAULT NULL,
  PRIMARY KEY (date_key, time_key, device_key, device_ip_key, ap_key, iface_key, vendor_key, ap_vendor_key, cnt_status_key),
  KEY device_wlan_connections_fact_device_key_IDX (device_key, event_timestamp DESC)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;