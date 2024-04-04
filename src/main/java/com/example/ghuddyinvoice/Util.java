package com.example.ghuddyinvoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static List<AvailableAccommodationPackageData> accommodation() {
        List<AvailableAccommodationPackageData> dataList = new ArrayList<>();

        // Static values for each object
        dataList.add(new AvailableAccommodationPackageData(
                1L,
                "Accommodation Name 1",
                "Standard",            // Room category
                "Couple Room",         // Room type
                true,
                2,
                1,                     // Bed count
                "King",                // Bed configuration
                BigDecimal.valueOf(50),
                2
        ));
        dataList.add(new AvailableAccommodationPackageData(
                2L,
                "Accommodation Name 2",
                "Standard",            // Room category
                "Couple Room",         // Room type
                true,
                2,
                1,                     // Bed count
                "King",                // Bed configuration
                BigDecimal.valueOf(60),
                3
        ));
        dataList.add(new AvailableAccommodationPackageData(
                3L,
                "Accommodation Name 3",
                "Standard",            // Room category
                "Couple Room",         // Room type
                true,
                2,
                1,                     // Bed count
                "King",                // Bed configuration
                BigDecimal.valueOf(70),
                4
        ));
        dataList.add(new AvailableAccommodationPackageData(
                4L,
                "Accommodation Name 4",
                "Standard",            // Room category
                "Couple Room",         // Room type
                true,
                2,
                1,                     // Bed count
                "King",                // Bed configuration
                BigDecimal.valueOf(80),
                1
        ));
        dataList.add(new AvailableAccommodationPackageData(
                5L,
                "Accommodation Name 5",
                "Standard",            // Room category
                "Couple Room",         // Room type
                true,
                2,
                1,                     // Bed count
                "King",                // Bed configuration
                BigDecimal.valueOf(90),
                5
        ));

        return dataList;
    }

    public static List<AvailableMealPackageData> food() {
        List<AvailableMealPackageData> foods = new ArrayList<>();

        // Creating and adding 5 objects to the list

        AvailableMealPackageData food1 = new AvailableMealPackageData();
        food1.setAvailableMealPackageId(1L);
        food1.setMealTypeId(101L);
        food1.setMealTypeName("Breakfast");
        food1.setMealPackagePrice(BigDecimal.valueOf(10.99));
        food1.setDayNumber(1);
        Map<Long, String> foodItems1 = new HashMap<>();
        foodItems1.put(1L, "Cereal");
        foodItems1.put(2L, "Milk");
        foodItems1.put(3L, "Toast");
        food1.setFoodItemDataList(foodItems1);
        foods.add(food1);

        AvailableMealPackageData food2 = new AvailableMealPackageData();
        food2.setAvailableMealPackageId(2L);
        food2.setMealTypeId(102L);
        food2.setMealTypeName("Lunch");
        food2.setMealPackagePrice(BigDecimal.valueOf(15.99));
        food2.setDayNumber(2);
        Map<Long, String> foodItems2 = new HashMap<>();
        foodItems2.put(1L, "Grilled Chicken");
        foodItems2.put(2L, "Steamed Vegetables");
        foodItems2.put(3L, "Rice");
        food2.setFoodItemDataList(foodItems2);
        foods.add(food2);

        AvailableMealPackageData food3 = new AvailableMealPackageData();
        food3.setAvailableMealPackageId(3L);
        food3.setMealTypeId(103L);
        food3.setMealTypeName("Dinner");
        food3.setMealPackagePrice(BigDecimal.valueOf(20.99));
        food3.setDayNumber(3);
        Map<Long, String> foodItems3 = new HashMap<>();
        foodItems3.put(1L, "Salmon");
        foodItems3.put(2L, "Mashed Potatoes");
        foodItems3.put(3L, "Green Beans");
        food3.setFoodItemDataList(foodItems3);
        foods.add(food3);

        AvailableMealPackageData food4 = new AvailableMealPackageData();
        food4.setAvailableMealPackageId(4L);
        food4.setMealTypeId(104L);
        food4.setMealTypeName("Snacks");
        food4.setMealPackagePrice(BigDecimal.valueOf(8.99));
        food4.setDayNumber(4);
        Map<Long, String> foodItems4 = new HashMap<>();
        foodItems4.put(1L, "Popcorn");
        foodItems4.put(2L, "Mixed Nuts");
        foodItems4.put(3L, "Granola Bar");
        food4.setFoodItemDataList(foodItems4);
        foods.add(food4);

        AvailableMealPackageData food5 = new AvailableMealPackageData();
        food5.setAvailableMealPackageId(5L);
        food5.setMealTypeId(105L);
        food5.setMealTypeName("Dessert");
        food5.setMealPackagePrice(BigDecimal.valueOf(12.99));
        food5.setDayNumber(5);
        Map<Long, String> foodItems5 = new HashMap<>();
        foodItems5.put(1L, "Chocolate Cake");
        foodItems5.put(2L, "Ice Cream");
        foodItems5.put(3L, "Fruit Salad");
        food5.setFoodItemDataList(foodItems5);
        foods.add(food5);

        return foods;
    }


}
