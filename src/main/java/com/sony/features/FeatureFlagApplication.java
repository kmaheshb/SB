package com.sony.features;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages= {"com.sony.features"})
public class FeatureFlagApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FeatureFlagApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }

}
