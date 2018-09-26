package com.pru.mapper.customconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.model.il.NBSCRTIREC.NBSCRTICONTRACTHDRDETAILS.BILLCD;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class BillingRenewalDateCustomConverter extends CustomConverter<String, BILLCD> {
	private final static Logger logger = LoggerFactory.getLogger(AssigneeDetailsCustomConverter.class);

	public BILLCD convert(String source, Type<? extends BILLCD> destinationType) {
		logger.info("BeneficiaryDetailsCustomConverter.convert() start");
		BILLCD bRenDate = new BILLCD();
		bRenDate.setCCYY("9999");
		bRenDate.setMM("99");
		bRenDate.setDD("99");
		return bRenDate;
	}
}
