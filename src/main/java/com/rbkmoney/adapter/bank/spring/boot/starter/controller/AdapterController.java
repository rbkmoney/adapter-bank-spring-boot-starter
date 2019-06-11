package com.rbkmoney.adapter.bank.spring.boot.starter.controller;

import com.rbkmoney.adapter.common.serializer.CallbackSerializer;
import com.rbkmoney.adapter.helpers.hellgate.HellgateAdapterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Handler callback 3DS */
@Slf4j
@RestController
@RequestMapping("/${server.rest.endpoint}")
public class AdapterController extends com.rbkmoney.adapter.common.controller.AdapterController {

    public AdapterController(HellgateAdapterClient hgClient, CallbackSerializer callbackSerializer) {
        super(hgClient, callbackSerializer);
    }

    @PostMapping(value = "term_url")
    public String receivePaymentIncomingParameters(HttpServletRequest servletRequest,
                                                   HttpServletResponse servletResponse) throws IOException {
        return super.receivePaymentIncomingParameters(servletRequest, servletResponse);
    }

    @PostMapping(value = "rec_term_url")
    public String receiveRecurrentIncomingParameters(HttpServletRequest servletRequest,
                                                     HttpServletResponse servletResponse) throws IOException {
        return super.receiveRecurrentIncomingParameters(servletRequest, servletResponse);
    }

}
