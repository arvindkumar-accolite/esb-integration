package com.pru.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pru.flink.consumer.FlinkJsonConsumer;

class NewBusinessPopulatorTest {
	private NewBusinessPopulator newBusinessPopulator;

	@BeforeEach
	void setUp() throws Exception {
		FlinkJsonConsumer consumer = new FlinkJsonConsumer();
		newBusinessPopulator = new NewBusinessPopulator();
	}

	@Test
	void testConvertIntegerToString() {
		assertNotNull(newBusinessPopulator.convertIntegerToString(12));
	}
}
