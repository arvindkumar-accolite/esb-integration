package com.pru.mapper.customconverter;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.constant.IntegrationConstants;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class ProposalDateCustomConvertor extends CustomConverter<String, BigInteger> {
	private final static Logger logger = LoggerFactory.getLogger(AssigneeDetailsCustomConverter.class);

	@Override
	public BigInteger convert(String source, Type<? extends BigInteger> destinationType) {
		logger.info("ProposalDateCustomConvertor.convert() start");
		String date = null;
		SimpleDateFormat sdf = null;
		SimpleDateFormat sdf1 = null;
		try {
			sdf = new SimpleDateFormat(IntegrationConstants.DATE_FORMAT_YYYYMMDD);
			sdf1 = new SimpleDateFormat(IntegrationConstants.CLTDOBX_FORMAT);
			date = sdf.format(sdf1.parse(source));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new BigInteger(date);
	}
}