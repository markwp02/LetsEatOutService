package com.markp.letseatoutservice.repository;

import com.markp.letseatoutservice.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> findAll();

    public Restaurant findById(int theId);

    public Boolean restaurantExists(int theId);

    public void save(Restaurant theRestaurant);

    public void deleteById(int theId);
}
