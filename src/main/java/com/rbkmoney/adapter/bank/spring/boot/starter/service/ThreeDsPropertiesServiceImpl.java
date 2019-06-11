package com.rbkmoney.adapter.bank.spring.boot.starter.service;

import com.rbkmoney.adapter.bank.spring.boot.starter.config.properties.AdapterProperties;
import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel;
import com.rbkmoney.adapter.common.constants.ThreeDsFields;
import com.rbkmoney.adapter.common.utils.converter.RedirectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThreeDsPropertiesServiceImpl implements ThreeDsPropertiesService {

    private final AdapterProperties adapterProperties;

    @Override
    public Map<String, String> initProperties(GeneralExitStateModel generalExitStateModel) {
        Map<String, String> params = new HashMap<>();
        params.put(ThreeDsFields.PA_REQ, generalExitStateModel.getPaReq());
        params.put(ThreeDsFields.MD, generalExitStateModel.getMd());
        params.put(ThreeDsFields.TERM_URL, RedirectUtils.getCallbackUrl(
                adapterProperties.getCallbackUrl(),
                adapterProperties.getPathCallbackUrl())
        );
        return params;
    }

}
