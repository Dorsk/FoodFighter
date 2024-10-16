package com.moura.foodfighter.Controlers;

import com.moura.foodfighter.Entities.Food;
import com.moura.foodfighter.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        return foodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food foodDetails) {
        return foodRepository.findById(id)
                .map(food -> {
                    food.setName(foodDetails.getName());
                    food.setDescription(foodDetails.getDescription());
                    food.setLat(foodDetails.getLat());
                    food.setLng(foodDetails.getLng());
                    food.setImage(foodDetails.getImage());
                    return ResponseEntity.ok(foodRepository.save(food));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}