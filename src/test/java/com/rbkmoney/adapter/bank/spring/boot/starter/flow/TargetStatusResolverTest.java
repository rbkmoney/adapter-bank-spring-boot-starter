package com.rbkmoney.adapter.bank.spring.boot.starter.flow;

import com.rbkmoney.adapter.common.enums.TargetStatus;
import com.rbkmoney.adapter.common.exception.UnknownTargetStatusException;
import com.rbkmoney.damsel.domain.*;
import org.junit.Assert;
import org.junit.Test;

public class TargetStatusResolverTest {

    private TargetStatusResolver targetStatusResolver = new TargetStatusResolver();

    @Test
    public void resolve() {
        TargetStatus resolve = targetStatusResolver.resolve(TargetInvoicePaymentStatus.cancelled(new InvoicePaymentCancelled()));
        Assert.assertEquals(TargetStatus.CANCELLED, resolve);

        resolve = targetStatusResolver.resolve(TargetInvoicePaymentStatus.captured(new InvoicePaymentCaptured()));
        Assert.assertEquals(TargetStatus.CAPTURED, resolve);

        resolve = targetStatusResolver.resolve(TargetInvoicePaymentStatus.processed(new InvoicePaymentProcessed()));
        Assert.assertEquals(TargetStatus.PROCESSED, resolve);

        resolve = targetStatusResolver.resolve(TargetInvoicePaymentStatus.refunded(new InvoicePaymentRefunded()));
        Assert.assertEquals(TargetStatus.REFUNDED, resolve);
    }

    @Test(expected = UnknownTargetStatusException.class)
    public void resolveException() {
        TargetStatus resolve = targetStatusResolver.resolve(null);
    }

}