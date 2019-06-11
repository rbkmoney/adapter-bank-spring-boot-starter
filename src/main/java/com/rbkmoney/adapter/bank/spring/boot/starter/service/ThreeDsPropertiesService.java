package com.rbkmoney.adapter.bank.spring.boot.starter.service;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel;

import java.util.Map;

public interface ThreeDsPropertiesService {

    Map<String, String> initProperties(GeneralExitStateModel generalExitStateModel);


}
