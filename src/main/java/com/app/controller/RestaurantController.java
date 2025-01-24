package com.app.controller;

import java.util.List;

import com.app.dao.RestaurantDao;
import com.app.entity.Restaurant;

public class RestaurantController {
    public static boolean addRestaurant(Restaurant restaurant) {
        return RestaurantDao.addRestaurant(restaurant);
    }

    public static List<Restaurant> getAllRestaurants() {
        return RestaurantDao.getAllRestaurants();
    }

    public static Restaurant getRestaurant(int id) {
        return RestaurantDao.getRestaurant(id);
    }

    public static boolean updateRestaurant(Restaurant restaurant) {
        return RestaurantDao.updateRestaurant(restaurant);
    }

    public static boolean deleteRestaurant(int id) {
        return RestaurantDao.deleteRestaurant(id);
    }
}
