
package com.Blog2.Blog2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Models.Group;
import Models.Post;
import Models.User;

@SpringBootApplication(scanBasePackages={"Service","DAO","Models","Controllers"})
@EnableJpaRepositories("DAO")
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {Group.class,Post.class,User.class})

public class Blog2Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Blog2Application.class, args);
	}
}
