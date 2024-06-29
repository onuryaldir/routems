package org.development.routems.service;

import org.development.routems.entity.RatingEntity;
import org.development.routems.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public RatingEntity saveRating(RatingEntity rating) {
        return ratingRepository.save(rating);
    }

    public List<RatingEntity> getAllRatings() {
        return ratingRepository.findAll();
    }

    public RatingEntity getRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}