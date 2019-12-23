package com.rbkmoney.adapter.bank.spring.boot.starter.backoff;

import com.rbkmoney.adapter.common.model.AdapterContext;
import com.rbkmoney.adapter.common.state.backoff.BackOffExecution;
import com.rbkmoney.adapter.common.state.backoff.ExponentialBackOff;
import com.rbkmoney.adapter.common.state.backoff.TimeOptionsExtractors;

import java.time.Instant;
import java.util.Map;

import static com.rbkmoney.adapter.common.state.backoff.ExponentialBackOff.*;

public class BackOffUtils {

    public static BackOffExecution prepareBackOffExecution(
            AdapterContext adapterContext,
            Map<String, String> options) {
        return exponentialBackOff(adapterContext, options)
                .start();
    }

    public static int prepareNextPollingInterval(
            AdapterContext adapterContext,
            Map<String, String> options) {
        return exponentialBackOff(adapterContext, options)
                .start()
                .nextBackOff()
                .intValue();
    }

    private static ExponentialBackOff exponentialBackOff(
            AdapterContext adapterContext,
            Map<String, String> options) {
        final Long currentLocalTime = Instant.now().toEpochMilli();

        Long startTime = adapterContext.getPollingInfo().getStartDateTimePolling() != null
                ? adapterContext.getPollingInfo().getStartDateTimePolling().toEpochMilli()
                : currentLocalTime;
        Integer exponential = TimeOptionsExtractors.extractExponent(options, DEFAULT_MUTIPLIER);
        Integer defaultInitialExponential = TimeOptionsExtractors.extractDefaultInitialExponential(options, DEFAULT_INITIAL_INTERVAL);
        Integer maxTimeBackOff = TimeOptionsExtractors.extractMaxTimeBackOff(options, DEFAULT_MAX_INTERVAL);

        return new ExponentialBackOff(
                startTime,
                currentLocalTime,
                exponential,
                defaultInitialExponential,
                maxTimeBackOff);
    }
}
