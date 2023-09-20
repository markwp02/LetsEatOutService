package com.markp.letseatoutservice.service;

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

    /**
     * Separate methods to add restaurant. This will force the save
     * of a new item in case a restaurant id is passed in the Restaurant object.
     * @param theRestaurant
     */
    @Override
    public void add(Restaurant theRestaurant) {
        theRestaurant.setRestaurantId(0);
        restaurantRepository.save(theRestaurant);
    }

    @Override
    public void update(Restaurant theRestaurant) {
        restaurantRepository.save(theRestaurant);
    }

    @Override
    public void deleteById(int theId) {
        Optional<Restaurant> result = restaurantRepository.findById(Integer.valueOf(theId));
        if(!result.isPresent()) {
            throw new RuntimeException("Restaurant not found - " + theId);
        }
        restaurantRepository.deleteById(theId);
    }
}
