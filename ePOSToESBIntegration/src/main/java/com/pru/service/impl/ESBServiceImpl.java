package com.pru.service.impl;

import com.pru.mapper.impl.NewBusinessPopulator;
import com.pru.model.middleware.NewBusinessModel;
import com.pru.service.ESBService;

public class ESBServiceImpl implements ESBService {

	public NewBusinessModel generateNBSModel(String json) {
		NewBusinessPopulator nbspop = new NewBusinessPopulator();
		NewBusinessModel nbsModel = nbspop.buildNewBusiness(json);
		return nbsModel;
	}

}
