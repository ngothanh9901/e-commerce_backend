package com.example.mediamarkbe;

import com.example.mediamarkbe.common.hibernate.auditor.SecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MediaMarkBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaMarkBeApplication.class, args);
	}

	@Bean
	public AuditorAware<Long> auditorAware() {
		return new SecurityAuditorAware();
	}
}
