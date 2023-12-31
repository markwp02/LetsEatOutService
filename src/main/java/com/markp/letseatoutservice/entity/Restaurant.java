package com.markp.letseatoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="restaurant_id")
    private int restaurantId;

    @Column(name="restaurant_name")
    private String restaurantName;

    @Column(name="restaurant_address")
    private String restaurantAddress;

    @Column(name="restaurant_cuisine")
    private String restaurantCuisine;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="restaurant_id")
    private List<Food> foodList;
}
