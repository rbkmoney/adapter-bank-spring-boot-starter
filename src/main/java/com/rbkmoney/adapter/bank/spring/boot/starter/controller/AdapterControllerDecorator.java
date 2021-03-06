package com.rbkmoney.adapter.bank.spring.boot.starter.controller;

import com.rbkmoney.adapter.common.controller.AdapterController;
import com.rbkmoney.adapter.common.state.deserializer.CallbackDeserializer;
import com.rbkmoney.adapter.common.state.serializer.CallbackSerializer;
import com.rbkmoney.adapter.helpers.hellgate.HellgateAdapterClient;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/${server.rest.endpoint}")
public class AdapterControllerDecorator {

    private AdapterController adapterController;

    public AdapterControllerDecorator(
            HellgateAdapterClient hgClient,
            CallbackSerializer callbackSerializer,
            CallbackDeserializer callbackDeserializer
    ) {
        adapterController = new AdapterController(hgClient, callbackSerializer, callbackDeserializer);
    }

    @PostMapping(value = "term_url")
    public String receivePaymentIncomingParameters(HttpServletRequest servletRequest,
                                                   HttpServletResponse servletResponse) throws IOException {
        return adapterController.receivePaymentIncomingParameters(servletRequest, servletResponse);
    }

    @PostMapping(value = "rec_term_url")
    public String receiveRecurrentIncomingParameters(HttpServletRequest servletRequest,
                                                     HttpServletResponse servletResponse) throws IOException {
        return adapterController.receiveRecurrentIncomingParameters(servletRequest, servletResponse);
    }

}
