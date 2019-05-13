package com.rbkmoney.adapter.bank.spring.boot.starter.processor;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.EntryStateModel;
import com.rbkmoney.adapter.bank.spring.boot.starter.model.ExitStateModel;

public interface Processor<T extends ExitStateModel, E extends EntryStateModel, R> {

    T process(R response, E context);

}
