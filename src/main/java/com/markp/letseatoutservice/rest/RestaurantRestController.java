package com.markp.letseatoutservice.rest;

import com.markp.letseatoutservice.entity.Restaurant;
import com.markp.letseatoutservice.repository.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        // force save of new item in case id is passed in body
        restaurant.setRestaurantId(0);
        restaurantService.save(restaurant);
        return restaurant;
    }

    @PutMapping("/restaurants")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.save(restaurant);
        return restaurant;
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public String deleteRestaurant(@PathVariable int restaurantId) {
        if(!restaurantService.restaurantExists(restaurantId)) {
            throw new RuntimeException("Restaurant not found - " + restaurantId);
        }
        restaurantService.deleteById(restaurantId);

        return "Deleted restaurant id - " + restaurantId;
    }
}
