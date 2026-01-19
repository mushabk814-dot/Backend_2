package com.example.practice.service;

import com.example.practice.model.LoveCalculation;
import com.example.practice.model.LoveRequest;
import com.example.practice.model.LoveResponse;
import com.example.practice.repository.LoveCalculationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class LoveCalculationService {

    @Autowired
    private LoveCalculationRepository loveCalculationRepository;

    private final Random random = new Random();
    private final Map<Integer, String[]> loveMessages = new HashMap<>();

    public LoveCalculationService() {
        // Initialize love messages
        loveMessages.put(10, new String[]{"Cheeee 🤢", "Bhut dekar love hai tumhara 😆 Mood kharab kar diya, jaao!"});
        loveMessages.put(25, new String[]{"Arey Yaar! 😒", "Kya pyaar hai yeh? Chai peene chale jaao!"});
        loveMessages.put(40, new String[]{"Thik Thak Hai 😅", "Zyaada umeed mat rakho! Friendzone ka risk hai!"});
        loveMessages.put(55, new String[]{"Acha Hai! 😊", "Pyaar ki shuruaat achhi hai! Thoda aur try karo!"});
        loveMessages.put(70, new String[]{"Waah! Mast Hai! 💕", "Tum dono ek dusre ke liye bane ho! Date pe chale jaao!"});
        loveMessages.put(85, new String[]{"Excellent! 😍", "Perfect match! Shaadi ki tayyari shuru karo!"});
        loveMessages.put(100, new String[]{"OMG! Soulmates! 💖", "Tumhare pyaar ki duniya amazing hai! Hollywood waala love story!"});
    }

    public LoveResponse calculateLove(LoveRequest request, HttpServletRequest httpRequest) {
        // Calculate love score
        int score = calculateLoveScore(request.getName1(), request.getName2());

        // Get message based on score
        String[] messageData = getMessageForScore(score);
        String message = messageData[0];
        String details = messageData[1];
        String emoji = extractEmoji(message);

        // Get client IP
        String ipAddress = getClientIp(httpRequest);

        // Save to MongoDB
        saveToDatabase(request.getName1(), request.getName2(), score, message, emoji, ipAddress);

        // Create response
        LoveResponse response = new LoveResponse(
                score,
                message,
                emoji,
                details,
                request.getName1() + " ❤️ " + request.getName2()
        );

        // Add MongoDB stats
        response.setCalculationId("MONGODB_" + new Date().getTime());

        return response;
    }

    private int calculateLoveScore(String name1, String name2) {
        String combined = (name1 + name2).toLowerCase().replaceAll("\\s+", "");

        int score = 0;
        for (int i = 0; i < combined.length(); i++) {
            char c = combined.charAt(i);
            score += (int) c * (i + 1);
        }

        // Add some randomness
        score = Math.abs(score + random.nextInt(50)) % 100;
        if (score == 0) score = 1;

        return score;
    }

    private String[] getMessageForScore(int score) {
        if (score <= 20) {
            return loveMessages.get(10);
        } else if (score <= 35) {
            return loveMessages.get(25);
        } else if (score <= 50) {
            return loveMessages.get(40);
        } else if (score <= 65) {
            return loveMessages.get(55);
        } else if (score <= 80) {
            return loveMessages.get(70);
        } else if (score <= 95) {
            return loveMessages.get(85);
        } else {
            return loveMessages.get(100);
        }
    }

    private String extractEmoji(String message) {
        if (message.contains("🤢")) return "🤢";
        if (message.contains("😒")) return "😒";
        if (message.contains("😅")) return "😅";
        if (message.contains("😊")) return "😊";
        if (message.contains("💕")) return "💕";
        if (message.contains("😍")) return "😍";
        if (message.contains("💖")) return "💖";
        return "❤️";
    }

    private void saveToDatabase(String name1, String name2, int score,
                                String message, String emoji, String ipAddress) {
        try {
            LoveCalculation calculation = new LoveCalculation(
                    name1, name2, score, message, emoji, ipAddress
            );

            loveCalculationRepository.save(calculation);
            System.out.println("✅ Saved to MongoDB: " + calculation);
        } catch (Exception e) {
            System.err.println("❌ MongoDB Save Error: " + e.getMessage());
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public LoveResponse getFunFact() {
        String[] funFacts = {
                "Pyaar andar se aata hai, calculator se nahi! 😊",
                "90% of pyaar is tolerating each other's weird habits! 😅",
                "True love is when you share your last piece of pizza! 🍕",
                "Pyaar ek aisi feeling hai jisme 2 log milke 3rd person ka mood kharab karte hai! 😆",
                "MongoDB me save ho raha hai tumhara pyaar! 💾"
        };

        String fact = funFacts[random.nextInt(funFacts.length)];
        String emoji = extractEmoji(fact);

        return new LoveResponse(101, "Fun Fact!", emoji, fact, "Love Ki Duniya");
    }

    // Get statistics from MongoDB
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        try {
            stats.put("totalCalculations", loveCalculationRepository.count());
            stats.put("highLoveCount", loveCalculationRepository.findByLoveScoreBetween(70, 100).size());
            stats.put("mediumLoveCount", loveCalculationRepository.findByLoveScoreBetween(30, 69).size());
            stats.put("lowLoveCount", loveCalculationRepository.findByLoveScoreBetween(1, 29).size());
            stats.put("recentCalculations", loveCalculationRepository.findTop10ByOrderByCalculationDateDesc());
            stats.put("database", "MongoDB Atlas");
            stats.put("timestamp", new Date());
        } catch (Exception e) {
            stats.put("error", "Could not fetch statistics: " + e.getMessage());
        }

        return stats;
    }
}