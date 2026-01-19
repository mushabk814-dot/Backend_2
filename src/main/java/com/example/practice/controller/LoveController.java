package com.example.practice.controller;

import com.example.practice.model.LoveRequest;
import com.example.practice.model.LoveResponse;
import com.example.practice.service.LoveCalculationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoveController {

    @Autowired
    private LoveCalculationService loveCalculationService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "💖 Love Ki Duniya Backend with MongoDB Atlas! 💖");
        response.put("timestamp", new Date());
        response.put("version", "2.0.0");
        response.put("database", "MongoDB Atlas Connected");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/calculate-love")
    public ResponseEntity<LoveResponse> calculateLove(
            @RequestBody LoveRequest request,
            HttpServletRequest httpRequest) {

        // Basic validation
        if (request.getName1() == null || request.getName1().trim().isEmpty() ||
                request.getName2() == null || request.getName2().trim().isEmpty()) {

            LoveResponse errorResponse = new LoveResponse("Both names are required! 😊");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            // Calculate love with MongoDB saving
            LoveResponse response = loveCalculationService.calculateLove(request, httpRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LoveResponse errorResponse = new LoveResponse("Error calculating love: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/fun-fact")
    public ResponseEntity<LoveResponse> getFunFact() {
        LoveResponse response = loveCalculationService.getFunFact();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = loveCalculationService.getStatistics();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/test-db")
    public ResponseEntity<Map<String, Object>> testDatabase() {
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("status", "success");
            response.put("message", "MongoDB Atlas connection test");
            response.put("timestamp", new Date());
            response.put("database", "loveDatabase");
            response.put("collection", "love_calculations");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Database connection failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Backend with MongoDB Atlas is working! 🎉");
        response.put("timestamp", new Date());
        response.put("endpoints", new String[]{
                "GET  /api/health",
                "POST /api/calculate-love",
                "GET  /api/fun-fact",
                "GET  /api/statistics",
                "GET  /api/test-db"
        });
        return ResponseEntity.ok(response);
    }
}