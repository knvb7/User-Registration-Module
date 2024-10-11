package com.trading.alphatrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@EnableCaching
@EnableSwagger2
@EnableWebSecurity
public class AlphatradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphatradingApplication.class, args);
	}

}
