package org.ippnat.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "integration.clients")
public class ClientProperties {

    private final RestTemplateProperties productsService;

    @ConstructorBinding
    public ClientProperties(RestTemplateProperties productsService) {
        this.productsService = productsService;
    }

}
