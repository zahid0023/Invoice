package com.example.ghuddyinvoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableAccommodationPackageData {
    @JsonProperty("tour_package_available_accommodation_package_id")
    private Long availableAccommodationPackageId;
    @JsonProperty("tour_package_accommodation_name")
    private String tourPackageAccommodationName;
    @JsonProperty("tour_package_room_category_name")
    private String tourPackageRoomCategoryName;
    @JsonProperty("tour_package_room_type_name")
    private String tourPackageRoomTypeName;
    @JsonProperty("is_room_shareable")
    private boolean isShareable;
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    @JsonProperty("bed_count")
    private Integer bedCount;
    @JsonProperty("bed_configuration")
    private String bedConfiguration;
    @JsonProperty("per_night_room_price")
    private BigDecimal unitPrice;
    @JsonProperty("night_number")
    private Integer nightNumber;

    public String getRoomDescription() {
        // Construct room description string
        StringBuilder description = new StringBuilder();
        description.append(tourPackageRoomCategoryName).append(" ")
                .append(tourPackageRoomTypeName).append(" - ")
                .append(isShareable ? "Shareable Room" : "Non Shareable Room").append(" - ")
                .append("Suitable for ").append(suitableForPersons).append(" persons").append(" - ")
                .append(bedCount).append(" " + bedConfiguration).append(" beds.");
        return description.toString();
    }


}
