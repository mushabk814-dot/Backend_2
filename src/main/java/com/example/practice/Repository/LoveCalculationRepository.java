package com.example.practice.repository;

import com.example.practice.model.LoveCalculation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoveCalculationRepository extends MongoRepository<LoveCalculation, String> {

    // Find by first name
    List<LoveCalculation> findByFirstName(String firstName);

    // Find by second name
    List<LoveCalculation> findBySecondName(String secondName);

    // Find by both names (case insensitive)
    List<LoveCalculation> findByFirstNameIgnoreCaseAndSecondNameIgnoreCase(String name1, String name2);

    // Find by score range
    List<LoveCalculation> findByLoveScoreBetween(int minScore, int maxScore);

    // Find top 10 recent calculations
    List<LoveCalculation> findTop10ByOrderByCalculationDateDesc();

    // Count total calculations
    long count();
}