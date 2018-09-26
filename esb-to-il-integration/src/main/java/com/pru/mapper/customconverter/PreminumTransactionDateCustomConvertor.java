package com.pru.mapper.customconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.model.il.NBSCRTIREC.NBSCRTIPREMIUMRCTDETAILS.TRANDATEX;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class PreminumTransactionDateCustomConvertor extends CustomConverter<String, TRANDATEX> {
	private final static Logger logger = LoggerFactory.getLogger(AssigneeDetailsCustomConverter.class);

	public TRANDATEX convert(String source, Type<? extends TRANDATEX> destinationType) {
		logger.info("PreminumTransactionDateCustomConvertor.convert() start");
		TRANDATEX tranDate = new TRANDATEX();
		tranDate.setCCYY("9999");
		tranDate.setMM("99");
		tranDate.setDD("99");
		return tranDate;
	}

}
