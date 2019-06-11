package com.rbkmoney.adapter.bank.spring.boot.starter.model;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.model.AdapterContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralExitStateModel {

    private Step nextStep;
    private String errorCode;
    private String errorMessage;
    private AdapterContext adapterContext;
    private GeneralEntryStateModel generalEntryStateModel;

    private Map<String, String> trxExtra;

}
