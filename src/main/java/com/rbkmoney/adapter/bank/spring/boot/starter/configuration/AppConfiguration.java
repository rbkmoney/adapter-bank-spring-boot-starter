package com.rbkmoney.adapter.bank.spring.boot.starter.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.common.mapper.SimpleErrorMapping;
import com.rbkmoney.adapter.common.mapper.SimpleObjectMapper;
import com.rbkmoney.error.mapping.ErrorMapping;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfiguration {

    @Value("${error-mapping.file}")
    private Resource errorMappingFilePath;

    @Value("${error-mapping.patternReason:\"'%s' - '%s'\"}")
    private String errorMappingPattern;

    @Bean
    public ErrorMapping errorMapping() throws IOException {
        return new SimpleErrorMapping(errorMappingFilePath, errorMappingPattern).createErrorMapping();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new SimpleObjectMapper().createSimpleObjectMapperFactory();
    }


}
