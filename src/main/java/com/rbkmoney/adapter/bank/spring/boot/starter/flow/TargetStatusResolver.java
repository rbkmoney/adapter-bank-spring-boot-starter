package com.rbkmoney.adapter.bank.spring.boot.starter.flow;

import com.rbkmoney.adapter.common.enums.TargetStatus;
import com.rbkmoney.adapter.common.utils.converter.PaymentDataConverterUtils;
import com.rbkmoney.damsel.domain.TargetInvoicePaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class TargetStatusResolver {

    public TargetStatus resolve(TargetInvoicePaymentStatus target) {
        return PaymentDataConverterUtils.getTargetStatus(target);
    }

}
