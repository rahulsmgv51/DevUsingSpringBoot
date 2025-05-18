package com.rahulsmgv.geoapp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemInfoRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private Data data;

    public static class Data {

        @JsonProperty("year")
        private int year;

        @JsonProperty("price")
        private double price;

        @JsonProperty("CPU model")
        private String cpuModel;

        @JsonProperty("Hard disk size")
        private String hardDiskSize;


    }
}