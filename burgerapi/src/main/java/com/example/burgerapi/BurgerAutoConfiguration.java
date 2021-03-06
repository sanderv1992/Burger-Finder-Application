package com.example.burgerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(BurgerProperties.class)
@PropertySource("classpath:burgerapi.properties")
public class BurgerAutoConfiguration {

    BurgerAutoConfiguration() {
    }

    @Configuration
    static class BurgerApiConfiguration {
        private final BurgerProperties properties;
        private final RestTemplate restTemplate;

        @Autowired
        BurgerApiConfiguration(
                BurgerProperties properties,
                RestTemplate restTemplate
        ) {
            this.properties = properties;
            this.restTemplate = restTemplate;
        }

        @Bean
        @ConditionalOnProperty("burger.api-url")
        public BurgerApi burgerApi() {
            return new BurgerApi(properties, restTemplate);
        }
    }
}
