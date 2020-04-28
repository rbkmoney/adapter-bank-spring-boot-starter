package com.rbkmoney.adapter.bank.spring.boot.starter.model;

import com.rbkmoney.adapter.common.enums.TargetStatus;
import com.rbkmoney.adapter.common.model.AdapterContext;
import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralEntryStateModel {

    @ToString.Exclude
    private String pan;
    @ToString.Exclude
    private Byte expMonth;
    @ToString.Exclude
    private Short expYear;
    @ToString.Exclude
    private String cvv2;
    @ToString.Exclude
    private String cardHolder;

    private String orderId;
    private Long amount;
    private Short currencyCode;
    private String currencySymbolCode;
    private String createdAt;

    private String ip;
    private String email;
    private String phone;

    private Long refundAmount;
    private String refundId;
    private String invoiceDetails;
    private String callbackUrl;

    @ToString.Exclude
    private Map<String, String> options;

    private AdapterContext adapterContext;
    private TargetStatus targetStatus;

    private String recToken;
    private boolean makeRecurrent;

    private String tokenProvider;
    private String cryptogram;
    private String eci;

    private String trxId;
    private Map<String, String> trxExtra;

}
