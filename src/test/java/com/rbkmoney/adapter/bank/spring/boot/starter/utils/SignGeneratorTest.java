package com.rbkmoney.adapter.bank.spring.boot.starter.utils;

import com.rbkmoney.adapter.common.utils.encryption.HmacEncryption;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import org.junit.Test;

public class SignGeneratorTest {

    @Test
    public void sign() throws NoSuchAlgorithmException {
        String test =
                "511.483USD677144616IT Books. Qty: 217Books Online Inc.14www.sample.com1512345678901234589999999919pgw@mail.sample.com11--142003010515302116F2B2DD7E603A7ADA33https://www.sample.com/shop/reply";

        String s = HmacEncryption.calculateHMacSha1(test, "00112233445566778899AABBCCDDEEFF");
        Assert.assertEquals("FACC882CA67E109E409E3974DDEDA8AAB13A5E48", s.toUpperCase());
    }
}