package com.rbkmoney.adapter.bank.spring.boot.starter.flow;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.StateModel;
import com.rbkmoney.adapter.common.enums.Step;

public interface StepResolver<T extends StateModel>  {

    Step resolveEntry(T stateModel);

    Step resolveExit(T stateModel);

}
