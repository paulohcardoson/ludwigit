package com.ludwigit.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.hashids")
@EnableConfigurationProperties
@Component
@Validated
@Data
public class HashIdConfig {

	private String salt;
	private Integer minLength;

}
