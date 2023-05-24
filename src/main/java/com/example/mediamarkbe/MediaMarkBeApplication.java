package com.example.mediamarkbe;

import com.example.mediamarkbe.common.hibernate.auditor.SecurityAuditorAware;
import com.example.mediamarkbe.config.AppProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties(AppProperties.class)
public class MediaMarkBeApplication {

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = MediaMarkBeApplication.class.getClassLoader();

		File file = new File(Objects.requireNonNull(classLoader.getResource("firebaseKey.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());


		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		// Kiểm tra FirebaseApp có tên DEFAULT đã tồn tại hay chưa
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		} else {
			FirebaseApp app = FirebaseApp.getInstance("[DEFAULT]");
			if (app == null) {
				FirebaseApp.initializeApp(options);
			}
		}


		SpringApplication.run(MediaMarkBeApplication.class, args);
	}
	@Bean
	public AuditorAware<Long> auditorAware() {
		return new SecurityAuditorAware();
	}
}
