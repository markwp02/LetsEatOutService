package com.markp.letseatoutservice.dao;

import com.markp.letseatoutservice.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
