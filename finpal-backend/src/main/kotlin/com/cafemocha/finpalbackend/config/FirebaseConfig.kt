package com.cafemocha.finpalbackend.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class FirebaseConfig {
    @Value("\${firebase.config.path")
    private lateinit var firebaseConfigPath: Resource

    @Bean
    fun firebaseInitialization() {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(firebaseConfigPath.inputStream))
            .build()
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        }
    }
}