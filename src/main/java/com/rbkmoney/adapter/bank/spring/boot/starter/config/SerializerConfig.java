package com.rbkmoney.adapter.bank.spring.boot.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.common.serializer.AdapterSerializer;
import com.rbkmoney.adapter.common.serializer.CallbackSerializer;
import com.rbkmoney.adapter.common.serializer.RecTokenSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SerializerConfig {

    @Bean
    public CallbackSerializer callbackSerializer(ObjectMapper objectMapper) {
        return new CallbackSerializer(objectMapper);
    }

    @Bean
    public AdapterSerializer adapterSerializer(ObjectMapper objectMapper) {
        return new AdapterSerializer(objectMapper);
    }

    @Bean
    public RecTokenSerializer recTokenSerializer(ObjectMapper objectMapper) {
        return new RecTokenSerializer(objectMapper);
    }

}
