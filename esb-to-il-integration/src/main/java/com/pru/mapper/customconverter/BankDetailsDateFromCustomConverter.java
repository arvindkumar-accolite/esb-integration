package com.pru.mapper.customconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.model.il.NBSCRTIREC.NBSCRTIBANKDETAILS.DATEFROM;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class BankDetailsDateFromCustomConverter extends CustomConverter<String, DATEFROM> {
	private final static Logger logger = LoggerFactory.getLogger(AssigneeDetailsCustomConverter.class);

	public DATEFROM convert(String source, Type<? extends DATEFROM> destinationType) {
		logger.info("BankDetailsDateFromCustomConverter.convert() start");
		DATEFROM dateFrom = new DATEFROM();
		dateFrom.setCcyy("9999");
		dateFrom.setMm("99");
		dateFrom.setDd("99");
		return dateFrom;
	}

}
