package com.example.practice.model;

import java.util.Date;

public class LoveResponse {
    private boolean success;
    private int score;
    private String message;
    private String emoji;
    private String details;
    private String names;
    private Date timestamp;

    // Empty constructor
    public LoveResponse() {
        this.timestamp = new Date();
    }

    // Main constructor
    public LoveResponse(int score, String message, String emoji,
                        String details, String names) {
        this.success = true;
        this.score = score;
        this.message = message;
        this.emoji = emoji;
        this.details = details;
        this.names = names;
        this.timestamp = new Date();
    }

    // Error constructor
    public LoveResponse(String errorMessage) {
        this.success = false;
        this.message = errorMessage;
        this.timestamp = new Date();
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setCalculationId(String s) {
    }
}