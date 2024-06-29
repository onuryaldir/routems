package org.development.routems.controller;

import org.development.routems.entity.RatingEntity;
import org.development.routems.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingEntity> createRating(@RequestBody RatingEntity rating) {
        RatingEntity savedRating = ratingService.saveRating(rating);
        return ResponseEntity.ok(savedRating);
    }

    @GetMapping
    public ResponseEntity<List<RatingEntity>> getAllRatings() {
        List<RatingEntity> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> getRatingById(@PathVariable Integer id) {
        RatingEntity rating = ratingService.getRatingById(id);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}