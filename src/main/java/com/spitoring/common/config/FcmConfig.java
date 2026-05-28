package com.spitoring.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Slf4j
@Configuration
public class FcmConfig {

    @Value("${app.fcm.credentials-path}")
    private String credentialsPath;

    @PostConstruct
    public void initialize() throws IOException {
        ClassPathResource resource = new ClassPathResource(credentialsPath);
        if (!resource.exists()) {
            log.warn("FCM 인증 파일이 없습니다. Firebase 초기화를 건너뜁니다. ({})", credentialsPath);
            return;
        }
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                .build();
            FirebaseApp.initializeApp(options);
            log.info("Firebase initialized");
        }
    }
}
