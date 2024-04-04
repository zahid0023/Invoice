package com.example.ghuddyinvoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableMealPackageData {
    @JsonProperty("tour_package_available_meal_package_id")
    private Long availableMealPackageId;
    @JsonProperty("meal_type_id")
    private Long mealTypeId;
    @JsonProperty("meal_type_name")
    private String mealTypeName;
    @JsonProperty("food_items")
    private Map<Long, String> foodItemDataList;
    @JsonProperty("meal_package_price")
    private BigDecimal mealPackagePrice;
    @JsonProperty("day_number")
    private Integer dayNumber;

    public String toFoodItems() {
        return this.foodItemDataList.values().stream().collect(Collectors.joining(","));
    }
}
