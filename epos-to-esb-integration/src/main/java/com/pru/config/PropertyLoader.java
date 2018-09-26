package com.pru.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.constant.IntegrationConstants;

public class PropertyLoader {
	private final static Logger logger = LoggerFactory.getLogger(PropertyLoader.class);
	private static Properties policyProposalPropConfig = new Properties();
	private static Properties flinkPropConfig = new Properties();
	static InputStream input = null;
	private static PropertyLoader instance = new PropertyLoader();

	public static PropertyLoader getInstance() {
		return instance;
	}

	static {
		String filename = IntegrationConstants.NBS_MAPPING_PROPERTIES;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			policyProposalPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	static {
		String filename = IntegrationConstants.FLINK_CONFIG_PROPERTIES;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			flinkPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	/**
	 * @return the policyProposalPropConfig
	 */
	public static Properties getPolicyProposalPropConfig() {
		return policyProposalPropConfig;
	}

	/**
	 * @return the flinkPropConfig
	 */
	public static Properties getFlinkPropConfig() {
		return flinkPropConfig;
	}

}
