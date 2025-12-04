package com.asmt.qa.rest.models.requests;

public record DummyjsonLoginRequest(String username,
                                    String password,
                                    int expiresInMins) {

}
