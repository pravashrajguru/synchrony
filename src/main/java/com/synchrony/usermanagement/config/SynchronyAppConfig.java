package com.synchrony.usermanagement.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.http.HttpClient;

@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class SynchronyAppConfig {
    @Value("${imgur.api.url}")
    public String imgurapi;
    @Value("${imgur.api.clientid}")
    String clientId;
    @Value("${image.upload.path}")
    String uploadPath;

    @Qualifier("httpClient")
    @Bean
    HttpClient hetHttpClient(){
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
}
