package com.rbkmoney.adapter.bank.spring.boot.starter.backoff;

import com.rbkmoney.adapter.common.model.AdapterContext;
import com.rbkmoney.adapter.common.state.backoff.BackOffExecution;
import com.rbkmoney.adapter.common.state.backoff.ExponentialBackOff;

import java.util.Map;

public class BackOffUtils {

    public static BackOffExecution prepareBackOffExecution(
            AdapterContext adapterContext,
            Map<String, String> options) {
        return new ExponentialBackOff(adapterContext, options)
                .start();
    }

    public static int prepareNextPollingInterval(
            AdapterContext adapterContext,
            Map<String, String> options) {
        return new ExponentialBackOff(adapterContext, options)
                .start()
                .nextBackOff()
                .intValue();
    }
}
