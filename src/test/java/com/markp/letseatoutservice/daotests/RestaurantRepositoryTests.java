package com.markp.letseatoutservice.daotests;

import com.markp.letseatoutservice.dao.RestaurantRepository;
import com.markp.letseatoutservice.entity.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

/**
 * JPA tests that use an embedded in memory H2 database instead of the one
 * configured in config
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantRepositoryTests {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveRestaurantTest() {

        Restaurant restaurant = Restaurant.builder()
                .restaurantName("Grill'd")
                .restaurantAddress("278 Clarendon St, South Melbourne")
                .restaurantCuisine("Burgers")
                .build();

        restaurantRepository.save(restaurant);

        Assertions.assertThat(restaurant.getRestaurantId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getRestaurantTest() {

        Restaurant restaurant = restaurantRepository.findById(1).get();

        Assertions.assertThat(restaurant.getRestaurantId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfRestaurantsTest() {

        List<Restaurant> restaurants = restaurantRepository.findAll();

        Assertions.assertThat(restaurants.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateRestaurantTest() {

        Restaurant restaurant = restaurantRepository.findById(1).get();

        restaurant.setRestaurantAddress("754 Glenferrie Rd, Hawthorn");

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        Assertions.assertThat(updatedRestaurant.getRestaurantAddress()).isEqualTo("754 Glenferrie Rd, Hawthorn");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteRestaurantTest() {

        restaurantRepository.deleteById(1);

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(1);

        Restaurant restaurant = null;

        if(optionalRestaurant.isPresent()) {
            restaurant = optionalRestaurant.get();
        }

        Assertions.assertThat(restaurant).isNull();
    }
}
