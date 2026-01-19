package com.example.practice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "love_calculations")
public class LoveCalculation {

    @Id
    private String id;

    @Field("name1")
    private String firstName;

    @Field("name2")
    private String secondName;

    @Field("love_score")
    private int loveScore;

    @Field("calculation_date")
    private Date calculationDate;

    @Field("result_message")
    private String resultMessage;

    @Field("emoji")
    private String emoji;

    @Field("ip_address")
    private String ipAddress;

    // Constructors
    public LoveCalculation() {}

    public LoveCalculation(String firstName, String secondName, int loveScore,
                           String resultMessage, String emoji, String ipAddress) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.loveScore = loveScore;
        this.resultMessage = resultMessage;
        this.emoji = emoji;
        this.ipAddress = ipAddress;
        this.calculationDate = new Date();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getLoveScore() {
        return loveScore;
    }

    public void setLoveScore(int loveScore) {
        this.loveScore = loveScore;
    }

    public Date getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(Date calculationDate) {
        this.calculationDate = calculationDate;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "LoveCalculation{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", loveScore=" + loveScore +
                ", calculationDate=" + calculationDate +
                ", resultMessage='" + resultMessage + '\'' +
                ", emoji='" + emoji + '\'' +
                '}';
    }
}