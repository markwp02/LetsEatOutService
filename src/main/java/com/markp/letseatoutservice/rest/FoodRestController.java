package com.markp.letseatoutservice.rest;

import com.markp.letseatoutservice.entity.Food;
import com.markp.letseatoutservice.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FoodRestController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/foodItems")
    public List<Food> findAll() {
        return foodService.findAll();
    }

    @GetMapping("/foodItems/{foodId}")
    public Food findById(@PathVariable int foodId) {
        Food food = foodService.findById(foodId);
        return food;
    }

    @PostMapping("/foodItems")
    public Food addFood(@RequestBody Food theFood) {
        foodService.add(theFood);
        return theFood;
    }

    @PutMapping("/foodItems")
    public Food updateFood(@RequestBody Food theFood) {
        foodService.update(theFood);
        return theFood;
    }

    @DeleteMapping("/foodItems/{foodId}")
    public String deleteFood(@PathVariable int foodId) {
        foodService.deleteById(foodId);

        return "Deleted food id - " + foodId;
    }
}
