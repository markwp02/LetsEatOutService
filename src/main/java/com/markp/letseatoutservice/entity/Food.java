package com.markp.letseatoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="food")
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="food_id")
    private int foodId;

    @Column(name="food_name")
    private String foodName;

    @Column(name="food_diet")
    private String foodDiet;

    @Column(name="food_rating")
    private int foodRating;
}
