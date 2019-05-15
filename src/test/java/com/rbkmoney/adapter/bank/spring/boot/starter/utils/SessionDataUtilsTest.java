package com.rbkmoney.adapter.bank.spring.boot.starter.utils;


import com.rbkmoney.damsel.cds.AuthData;
import com.rbkmoney.damsel.cds.CardSecurityCode;
import com.rbkmoney.damsel.cds.SessionData;
import org.junit.Assert;
import org.junit.Test;

public class SessionDataUtilsTest {

    public static final String CARD_SECURITY_CODE = "adfdfs";

    @Test
    public void getCvv2() {
        SessionData sessionData = new SessionData();
        String cvv2 = SessionDataUtils.getCvv2(sessionData);

        Assert.assertNull(cvv2);

        AuthData authData = new AuthData();
        sessionData.setAuthData(authData);
        cvv2 = SessionDataUtils.getCvv2(sessionData);

        Assert.assertNull(cvv2);

        CardSecurityCode cardSecurityCode = new CardSecurityCode();
        authData.setCardSecurityCode(cardSecurityCode);
        cvv2 = SessionDataUtils.getCvv2(sessionData);

        Assert.assertNull(cvv2);

        cardSecurityCode.setValue(CARD_SECURITY_CODE);

        cvv2 = SessionDataUtils.getCvv2(sessionData);

        Assert.assertEquals(CARD_SECURITY_CODE, cvv2);
    }
}