package com.rbkmoney.adapter.bank.spring.boot.starter.flow;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralEntryStateModel;
import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel;
import com.rbkmoney.adapter.common.enums.Step;

public interface StepResolver<T extends GeneralEntryStateModel, R extends GeneralExitStateModel> {

    Step resolveEntry(T entryStateModel);

    Step resolveExit(R exitStateModel);

}
