package com.pru.service;

import com.pru.model.middleware.NewBusinessModel;

public interface ESBService {
	public NewBusinessModel generateNBSModel(String json);
}
