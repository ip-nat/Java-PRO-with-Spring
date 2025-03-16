package org.ippnat.config;

import org.ippnat.config.properties.ClientProperties;
import org.ippnat.config.properties.RestTemplateProperties;
import org.ippnat.controller.RestTemplateResponseErrorHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({ClientProperties.class})
public class ApplicationConfig {

    private final ClientProperties clientProperties;

    public ApplicationConfig(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @Bean
    public RestTemplate paymentsClient(RestTemplateResponseErrorHandler errorHandler) {
        RestTemplateProperties properties = clientProperties.getProductsService();
        return new RestTemplateBuilder()
                .rootUri(properties.getUrl())
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }

}
