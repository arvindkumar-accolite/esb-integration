package com.pru.constant;

import java.io.File;

public class IntegrationConstants {
	public static final String GROUP_ID = "group.id";
	public static final String POLICY_PROPSAL_TOPIC = "policy.proposal.topic";
	public static final String NBS_PROPSAL_TOPIC = "newBusiness.proposal.topic";
	public static final String BOOTSTRAP_SERVER = "bootstrap.servers";
	public static final String ZOOKEEPER_CONNECT = "zookeeper.connect";
	public static final String NBS_MAPPING_PROPERTIES = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"policy-proposal"+File.separator+"epos-to-policyproposal-mapping.properties";
	public static final String FLINK_CONFIG_PROPERTIES = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"flink-config"+File.separator+"config.properties";
	public static final String CLTDOBX_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String RESOURCE_PATH = "path";

}