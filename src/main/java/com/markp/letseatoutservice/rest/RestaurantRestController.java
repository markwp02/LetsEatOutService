package com.markp.letseatoutservice.rest;

import com.markp.letseatoutservice.entity.Restaurant;
import com.markp.letseatoutservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable int restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return restaurant;
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.add(restaurant);
        return restaurant;
    }

    @PutMapping("/restaurants")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.update(restaurant);
        return restaurant;
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public String deleteRestaurant(@PathVariable int restaurantId) {
        restaurantService.deleteById(restaurantId);

        return "Deleted restaurant id - " + restaurantId;
    }
}
