package com.example.ghuddyinvoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTransferPackageData {
    @JsonProperty("tour_package_available_transfer_package_id")
    private Long availableTransferPackageId;
    @JsonProperty("tour_package_transfer_route")
    private String transferRoute;
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @JsonProperty("is_ac")
    private Boolean isAc;
    @JsonProperty("trip_type")
    private TripType tripType;
    @JsonProperty("per_vehicle_per_trip_price")
    private BigDecimal unitPrice;
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;

    public String toDescription(){
        return new StringBuilder()
                .append("Vehicle Type: ")
                .append(this.transportationModeName)
                .append(", ")
                .append("Transportation Provider: ")
                .append(this.transportationProviderName)
                .toString();
    }

}
