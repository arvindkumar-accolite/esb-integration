package com.pru.mapper.customconverter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.model.esb.QuestionDetails;
import com.pru.model.il.NBSCRTIREC.NBSCRTIQUESTION;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class QuestionDetailsCustomConverter extends CustomConverter<List<QuestionDetails>, List<NBSCRTIQUESTION>> {
	private final static Logger logger = LoggerFactory.getLogger(AssigneeDetailsCustomConverter.class);

	public List<NBSCRTIQUESTION> convert(List<QuestionDetails> source,
			Type<? extends List<NBSCRTIQUESTION>> destinationType) {
		logger.info("QuestionDetailsCustomConverter.convert() start");
		if (null == source) {
			return null;
		}
		List<NBSCRTIQUESTION> targetQuestione = new ArrayList<>();
		for (QuestionDetails questionDetails : source) {
			NBSCRTIQUESTION targetQuestioneDetails = new NBSCRTIQUESTION();
			targetQuestioneDetails.setANSWER(questionDetails.getAnswer());
			targetQuestioneDetails.setQUESTIONENTID(questionDetails.getQuestionId());
			targetQuestioneDetails.setQUESTIONPARENT(questionDetails.getQuestionParent());
			targetQuestione.add(targetQuestioneDetails);
		}
		return targetQuestione;
	}

}
