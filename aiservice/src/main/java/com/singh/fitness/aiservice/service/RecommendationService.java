package com.singh.fitness.aiservice.service;

import com.singh.fitness.aiservice.model.Recommendation;
import com.singh.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;


    public List<Recommendation> getUserRecommendatio(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendatio(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for activity: "+ activityId));
    }
}
