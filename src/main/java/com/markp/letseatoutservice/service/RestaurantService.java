package com.markp.letseatoutservice.service;

import com.markp.letseatoutservice.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> findAll();

    public Restaurant findById(int theId);

    public void add(Restaurant theRestaurant);

    public void update(Restaurant theRestaurant);

    public void deleteById(int theId);
}
