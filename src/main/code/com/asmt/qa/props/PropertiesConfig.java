package com.asmt.qa.props;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DummyjsonProperties.class)
public class PropertiesConfig {
}
