package com.asmt.qa.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DummyjsonLoginResponse(String accessToken,
                                     String refreshToken,
                                     Integer id,
                                     String username,
                                     String email,
                                     String firstName,
                                     String lastName,
                                     String gender,
                                     String image) {
}
