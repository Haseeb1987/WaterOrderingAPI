package com.rubincon.code.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)  
@EnableScheduling
public class WaterOrderingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterOrderingApiApplication.class, args);
	}

	@Bean
    public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource  source = new ResourceBundleMessageSource();
        source.setBasenames("message");
        return source;
    }
}
