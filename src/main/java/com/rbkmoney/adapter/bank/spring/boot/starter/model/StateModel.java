package com.rbkmoney.adapter.bank.spring.boot.starter.model;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.enums.TargetStatus;
import com.rbkmoney.adapter.common.model.AdapterContext;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateModel {

    private AdapterContext adapterContext;
    private TargetStatus targetStatus;
    private Step step;
    private Step nextStep;

    private String recToken;
    private boolean makeRecurrent;

}
