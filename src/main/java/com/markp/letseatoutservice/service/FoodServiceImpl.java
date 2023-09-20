package com.markp.letseatoutservice.service;

import com.markp.letseatoutservice.dao.FoodRepository;
import com.markp.letseatoutservice.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food findById(int theId) {
        Optional<Food> result = foodRepository.findById(theId);

        Food food;
        if (result.isPresent()) {
            food = result.get();
        } else {
            throw new RuntimeException("Did not find food with id - " + theId);
        }
        return food;
    }

    /**
     * Separate methods to add food. This will force the save
     * of a new item in case a food id is passed in the Food object.
     * @param theFood
     */
    @Override
    public void add(Food theFood) {
        theFood.setFoodId(0);
        foodRepository.save(theFood);
    }

    @Override
    public void update(Food theFood) {
        foodRepository.save(theFood);
    }

    @Override
    public void deleteById(int theId) {
        Optional<Food> result = foodRepository.findById(theId);

        if(!result.isPresent()){
            throw new RuntimeException("Food not found - " + theId);
        }
        foodRepository.deleteById(theId);
    }
}
