package com.markp.letseatoutservice.service;

import com.markp.letseatoutservice.entity.Food;

import java.util.List;

public interface FoodService {

    public List<Food> findAll();

    public Food findById(int theId);

    public void add(Food theFood);

    public void update(Food theFood);

    public void deleteById(int theId);
}
