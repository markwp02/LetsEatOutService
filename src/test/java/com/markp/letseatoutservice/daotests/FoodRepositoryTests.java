package com.markp.letseatoutservice.daotests;

import com.markp.letseatoutservice.dao.FoodRepository;
import com.markp.letseatoutservice.entity.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class FoodRepositoryTests {

    @Autowired
    FoodRepository foodRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveFoodTest() {

        Food food = Food.builder()
                .foodName("Vegan Garden Goodness")
                .foodDiet("Vegan")
                .foodRating(5)
                .build();

        foodRepository.save(food);

        Assertions.assertThat(food.getFoodId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getRestaurantTest() {

        Food food = foodRepository.findById(1).get();

        Assertions.assertThat(food.getFoodId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfRestaurantsTest() {

        List<Food> foods = foodRepository.findAll();

        Assertions.assertThat(foods.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateRestaurantTest() {

        Food food = foodRepository.findById(1).get();

        food.setFoodName("Vegan Zesty Mexi");

        Food updatedFood = foodRepository.save(food);

        Assertions.assertThat(updatedFood.getFoodName()).isEqualTo("Vegan Zesty Mexi");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteRestaurantTest() {

        foodRepository.deleteById(1);

        Optional<Food> optionalFood = foodRepository.findById(1);

        Food food = null;

        if(optionalFood.isPresent()) {
            food = optionalFood.get();
        }

        Assertions.assertThat(food).isNull();
    }
}
