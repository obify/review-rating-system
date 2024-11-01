package com.obify.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//We don't want spring to configure default login form with default credentials hence below exclude
@SpringBootApplication(
		exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class}
)
public class ReviewRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewRatingApplication.class, args);
	}

}
