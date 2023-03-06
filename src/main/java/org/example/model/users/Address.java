package org.example.model.users;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String city;
    private String country;
    private Coordinate coordinate;
    private double zipcode;
}
