package com.hcl.liberaryManager;

import com.hcl.liberaryManager.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class LiberaryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiberaryManagerApplication.class, args);
	}

}
