package com.pru.config;

import java.io.IOException;

import org.apache.flink.api.java.utils.ParameterTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.constant.IntegrationConstants;

public class PropertyLoader {
	private final static Logger logger = LoggerFactory.getLogger(PropertyLoader.class);
	private static ParameterTool policyProposalPropConfig; 
	private static ParameterTool flinkPropConfig;
	private static String basePath;

	public PropertyLoader(String path) {
		basePath = path;
		loadProperties(path);
	}

	private void loadProperties(String path) {
		logger.info("PropertyLoader start");
		try {
			policyProposalPropConfig = ParameterTool
					.fromPropertiesFile(path + IntegrationConstants.NBS_MAPPING_PROPERTIES);
			flinkPropConfig = ParameterTool
					.fromPropertiesFile(path + IntegrationConstants.NBS_MAPPING_PROPERTIES);
			
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}",e);
			e.printStackTrace();
		}
	}

	/**
	 * @return the policyProposalPropConfig
	 */
	public static ParameterTool getPolicyProposalPropConfig() {
		return policyProposalPropConfig;
	}

	/**
	 * @return the basePath
	 */
	public static String getBasePath() {
		return basePath;
	}

	/**
	 * @return the flinkPropConfig
	 */
	public static ParameterTool getFlinkPropConfig() {
		return flinkPropConfig;
	}
	
}
