package com.rbkmoney.adapter.bank.spring.boot.starter.service;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel;
import com.rbkmoney.adapter.common.constants.ThreeDsFields;
import com.rbkmoney.adapter.common.model.AdapterContext;
import com.rbkmoney.adapter.common.properties.CommonAdapterProperties;
import com.rbkmoney.adapter.common.utils.converter.RedirectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThreeDsPropertiesServiceImpl implements ThreeDsPropertiesService {

    private final CommonAdapterProperties adapterProperties;

    @Override
    public Map<String, String> initProperties(GeneralExitStateModel generalExitStateModel) {
        Map<String, String> params = new HashMap<>();
        AdapterContext adapterContext = generalExitStateModel.getAdapterContext();
        params.put(ThreeDsFields.PA_REQ, adapterContext.getPaReq());
        params.put(ThreeDsFields.MD, adapterContext.getMd());
        params.put(ThreeDsFields.TERM_URL, RedirectUtils.getCallbackUrl(
                adapterProperties.getCallbackUrl(),
                adapterProperties.getPathCallbackUrl())
        );
        return params;
    }

}
