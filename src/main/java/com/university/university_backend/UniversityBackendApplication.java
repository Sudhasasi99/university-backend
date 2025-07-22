package com.university.university_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.university.university_backend.entity.AppUser;
import com.university.university_backend.repository.AppUserRepository;

@SpringBootApplication
public class UniversityBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniversityBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDefaultAdmin(AppUserRepository appUserRepository) {
        return args -> {
            String adminEmail = "admin@university.com";
            String adminPassword = "admin123";
            String adminName = "Default Admin";
            if (appUserRepository.findByEmail(adminEmail).isEmpty()) {
                AppUser admin = new AppUser();
                admin.setName(adminName);
                admin.setEmail(adminEmail);
                admin.setPassword(new BCryptPasswordEncoder().encode(adminPassword));
                admin.setRole("ADMIN");
                admin.setStatus("Active");
                appUserRepository.save(admin);
                System.out.println("Default admin user created: " + adminEmail + " / " + adminPassword);
            }
        };
    }
}
