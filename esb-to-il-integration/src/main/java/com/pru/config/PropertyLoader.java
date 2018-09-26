package com.pru.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.constant.IntegrationConstants;

public class PropertyLoader {
	private final static Logger logger = LoggerFactory.getLogger(PropertyLoader.class);

	private static Properties newBizProposalPropConfig = new Properties();
	private static Properties createClientPropConfig = new Properties();
	private static Properties ilPropConfig = new Properties();
	private static Properties newBizProposalCustomPropConfig = new Properties();
	private static Properties createClientCustomPropConfig = new Properties();
	private static Properties newBizProposalFlinkPropConfig = new Properties();
	private static PropertyLoader instance = new PropertyLoader();
	static InputStream input = null;

	public static PropertyLoader getInstance() {
		return instance;
	}

	static {
		String filename = IntegrationConstants.FLINK_KAFKA_CONFIG_LOCATION;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			newBizProposalFlinkPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	static {
		String filename = IntegrationConstants.PROPOSAL_TO_NEWBIZMAPPING_PROP_LOCATION;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			newBizProposalPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	static {
		String filename = IntegrationConstants.PROPOSAL_TO_CLIENT_PROP_LOCATION;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			createClientPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	static {
		String filename = IntegrationConstants.IL_CONFIG_PROP_LOCATION;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			ilPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	static {
		String filename = IntegrationConstants.PROPOSAL_TO_CLIENT_CUSTOM_PROP_LOCATION;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			createClientCustomPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	static {
		String filename = IntegrationConstants.PROPOSAL_TO_NEWBIZMAPPING_CUSTOM_PROP_LOCATION;
		logger.info("PropertyLoader start: " + filename);
		input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			newBizProposalCustomPropConfig.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Error while loading property in PropertyLoader :: {}", e);
		}
	}

	/**
	 * @return the newBizProposalPropConfig
	 */
	public static Properties getNewBizProposalPropConfig() {
		return newBizProposalPropConfig;
	}

	/**
	 * @return the createClientPropConfig
	 */
	public static Properties getCreateClientPropConfig() {
		return createClientPropConfig;
	}

	/**
	 * @return the ilPropConfig
	 */
	public static Properties getIlPropConfig() {
		return ilPropConfig;
	}

	/**
	 * @return the newBizProposalCustomPropConfig
	 */
	public static Properties getNewBizProposalCustomPropConfig() {
		return newBizProposalCustomPropConfig;
	}

	/**
	 * @return the createClientCustomPropConfig
	 */
	public static Properties getCreateClientCustomPropConfig() {
		return createClientCustomPropConfig;
	}

	/**
	 * @return the newBizProposalFlinkPropConfig
	 */
	public static Properties getNewBizProposalFlinkPropConfig() {
		return newBizProposalFlinkPropConfig;
	}

}
