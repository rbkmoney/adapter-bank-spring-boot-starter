package com.rbkmoney.adapter.bank.spring.boot.starter.config;

import com.rbkmoney.adapter.common.component.NetworkFilterComponent;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NetworkConfig {

    @Value("${server.rest.port}")
    private int restPort;

    @Value("/${server.rest.endpoint}/")
    private String restEndpoint;

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
