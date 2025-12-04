package com.asmt.qa.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dummyjson")
public record DummyjsonProperties(String url) {

}
