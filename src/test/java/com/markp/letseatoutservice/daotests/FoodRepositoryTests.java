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

    final String FOOD_NAME = "Vegan Garden Goodness";
    final String FOOD_DIET = "Vegan";
    final int FOOD_RATING = 5;

    final int TARGET_INDEX = 1;
    final int FOODS_STORED = 1;
    final int UNASSIGNED_INDEX = 0;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveFoodTest() {

        Food food = Food.builder()
                .foodName(FOOD_NAME)
                .foodDiet(FOOD_DIET)
                .foodRating(FOOD_RATING)
                .build();

        foodRepository.save(food);
        Assertions.assertThat(food.getFoodId()).isGreaterThan(UNASSIGNED_INDEX);
    }

    @Test
    @Order(2)
    public void getRestaurantTest() {

        Food food = foodRepository.findById(TARGET_INDEX).get();
        Assertions.assertThat(food.getFoodId()).isEqualTo(TARGET_INDEX);
    }

    @Test
    @Order(3)
    public void getListOfRestaurantsTest() {

        List<Food> foods = foodRepository.findAll();
        Assertions.assertThat(foods.size()).isEqualTo(FOODS_STORED);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateRestaurantTest() {

        String updatedFoodName = "Vegan Zesty Mexi";
        Food food = foodRepository.findById(TARGET_INDEX).get();

        food.setFoodName(updatedFoodName);
        Food updatedFood = foodRepository.save(food);
        Assertions.assertThat(updatedFood.getFoodName()).isEqualTo(updatedFoodName);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteRestaurantTest() {

        foodRepository.deleteById(TARGET_INDEX);
        Optional<Food> optionalFood = foodRepository.findById(TARGET_INDEX);

        Food food = null;
        if(optionalFood.isPresent()) {
            food = optionalFood.get();
        }
        Assertions.assertThat(food).isNull();
    }
}
