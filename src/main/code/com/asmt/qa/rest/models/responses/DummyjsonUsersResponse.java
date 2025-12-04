package com.asmt.qa.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DummyjsonUsersResponse(List<User> users, Integer total, Integer skip, Integer limit) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record User(
        Integer id,
        String firstName,
        String lastName,
        String maidenName,
        Integer age,
        String gender,
        String email,
        String phone,
        String username,
        String password,
        String birthDate,
        String image,
        String bloodGroup,
        Double height,
        Double weight,
        String eyeColor,
        Hair hair,
        String ip,
        Address address,
        String macAddress,
        String university,
        Bank bank,
        Company company,
        String ein,
        String ssn,
        String userAgent,
        Crypto crypto,
        String role
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Hair(String color, String type) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Address(
        String address,
        String city,
        String state,
        String stateCode,
        String postalCode,
        Coordinates coordinates,
        String country
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Coordinates(Double lat, Double lng) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Bank(
        String cardExpire,
        String cardNumber,
        String cardType,
        String currency,
        String iban
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Company(
        String department,
        String name,
        String title,
        CompanyAddress address
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CompanyAddress(
        String address,
        String city,
        String state,
        String stateCode,
        String postalCode,
        Coordinates coordinates,
        String country
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Crypto(String coin, String wallet, String network) {}
}
