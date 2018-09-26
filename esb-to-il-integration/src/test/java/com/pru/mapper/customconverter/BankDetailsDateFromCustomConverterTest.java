package com.pru.mapper.customconverter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankDetailsDateFromCustomConverterTest {
	BankDetailsDateFromCustomConverter bankDetailsDateFromCustomConverter;

	@BeforeEach
	void setUp() throws Exception {
		bankDetailsDateFromCustomConverter = new BankDetailsDateFromCustomConverter();
	}

	@Test
	void test() {
		bankDetailsDateFromCustomConverter.convert("9999/99/99", null);
		assertTrue(true);
	}

}
