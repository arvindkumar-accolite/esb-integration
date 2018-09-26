package com.pru.mapper.customconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.model.il.NBSCRTIREC.NBSCRTIMANDATEDETAILS.EFFDATEN264679;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class MandatoryEffectiveDateCustomConvertor extends CustomConverter<String, EFFDATEN264679> {
	private final static Logger logger = LoggerFactory.getLogger(AssigneeDetailsCustomConverter.class);

	public EFFDATEN264679 convert(String source, Type<? extends EFFDATEN264679> destinationType) {
		logger.info("MandatoryEffectiveDateCustomConvertor.convert() start");
		EFFDATEN264679 effDate = new EFFDATEN264679();
		effDate.setCCYY("9999");
		effDate.setMM("99");
		effDate.setDD("99");
		return effDate;
	}

}
