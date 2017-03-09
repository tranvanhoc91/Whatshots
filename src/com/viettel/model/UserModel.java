package com.viettel.model;

import com.viettel.common.ActionEvent;
import com.viettel.common.ErrorConstants;
import com.viettel.common.ModelEvent;
import com.viettel.controller.UserController;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPResponse;

public class UserModel extends AbstractModelService {
	protected static UserModel instance;

	protected UserModel() {
	}

	public static UserModel getInstance() {
		if (instance == null) {
			instance = new UserModel();
		}
		return instance;
	}

	
	public void onReceiveData(HTTPMessage mes) {
		ActionEvent actionEvent = (ActionEvent) mes.getUserData();
		ModelEvent model = new ModelEvent();
		model.setDataText(mes.getDataText());
		model.setCode(mes.getCode());
		model.setParams(((HTTPResponse) mes).getRequest().getDataText());
		model.setActionEvent(actionEvent);
		// DMD check null or empty
		if (StringUtil.isNullOrEmpty((String) mes.getDataText())) {
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			UserController.getInstance().handleErrorModelEvent(model);
			return;
		}
		switch (mes.getAction()) {
		}
	}
		
	

	public void onReceiveError(HTTPResponse response) {
		ActionEvent actionEvent = (ActionEvent) response.getUserData();
		
		ModelEvent model = new ModelEvent();
		model.setDataText(response.getDataText());
		model.setParams(((HTTPResponse) response).getRequest().getDataText());
		model.setActionEvent(actionEvent);
		
		model.setModelCode(ErrorConstants.ERROR_NO_CONNECTION);
		model.setModelMessage(response.getErrMessage());
		UserController.getInstance().handleErrorModelEvent(model);

	}
}
