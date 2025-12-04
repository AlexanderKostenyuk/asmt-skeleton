package com.asmt.qa.rest.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DummyjsonEndpoints {
    LOGIN("/auth/login"),
    USERS("/users");

    private final String path;
}
