package com.asmt.qa.cucumber;

import com.asmt.qa.props.PropertiesConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(basePackages = "com.asmt.qa")
@Import({PropertiesConfig.class})
public class TestBootConfig {
}
