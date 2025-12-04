package com.asmt.qa.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TestBootConfig.class)
public class CucumberSpringConfiguration {
}
