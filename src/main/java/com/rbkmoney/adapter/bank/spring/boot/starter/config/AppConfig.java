package com.rbkmoney.adapter.bank.spring.boot.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.common.component.NetworkFilterComponent;
import com.rbkmoney.adapter.common.mapper.SimpleErrorMapping;
import com.rbkmoney.adapter.common.mapper.SimpleObjectMapper;
import com.rbkmoney.error.mapping.ErrorMapping;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Value("${error-mapping.file}")
    private Resource errorMappingFilePath;

    @Value("${error-mapping.patternReason:\"'%s' - '%s'\"}")
    private String errorMappingPattern;

    @Value("${restTemplate.networkTimeout}")
    private int networkTimeout;

    @Value("${server.rest.port}")
    private int restPort;

    @Value("/${server.rest.endpoint}/")
    private String restEndpoint;

    @Bean
    public ErrorMapping errorMapping() throws IOException {
        return new SimpleErrorMapping(errorMappingFilePath, errorMappingPattern).getErrorMapping();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new SimpleObjectMapper().getSimpleObjectMapper();
    }

    @Bean
    public FilterRegistrationBean externalPortRestrictingFilter() {
        return new NetworkFilterComponent().externalPortRestrictingFilter(restPort, restEndpoint);
    }

    @Bean
    public FilterRegistrationBean woodyFilter() {
        return new NetworkFilterComponent().woodyFilter(restPort, restEndpoint);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        Connector connector = new Connector();
        connector.setPort(restPort);
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }

}
