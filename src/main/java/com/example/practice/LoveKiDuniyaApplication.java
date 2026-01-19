package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoveKiDuniyaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoveKiDuniyaApplication.class, args);
        System.out.println("=========================================");
        System.out.println("💖 LOVE KI DUNIYA BACKEND STARTED! 💖");
        System.out.println("🌐 http://localhost:8080");
        System.out.println("📡 APIs:");
        System.out.println("   GET  /api/health");
        System.out.println("   POST /api/calculate-love");
        System.out.println("   GET  /api/fun-fact");
        System.out.println("=========================================");
    }
}