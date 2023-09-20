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

    @Override
    public void save(Food theFood) {
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
