package com.markp.letseatoutservice.repository;

import com.markp.letseatoutservice.dao.RestaurantRepository;
import com.markp.letseatoutservice.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(int theId) {
        Optional<Restaurant> result = restaurantRepository.findById(Integer.valueOf(theId));

        Restaurant restaurant;
        if(result.isPresent()) {
            restaurant = result.get();
        } else {
            throw new RuntimeException("Did not find restaurant with id - " + theId);
        }

        return restaurant;
    }

    @Override
    public Boolean restaurantExists(int theId) {
        Optional<Restaurant> result = restaurantRepository.findById(Integer.valueOf(theId));
        return result.isPresent();
    }

    @Override
    public void save(Restaurant theRestaurant) {
        restaurantRepository.save(theRestaurant);
    }

    @Override
    public void deleteById(int theId) {
        restaurantRepository.deleteById(theId);
    }
}
