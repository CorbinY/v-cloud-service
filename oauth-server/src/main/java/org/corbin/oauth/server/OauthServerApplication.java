package org.corbin.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** @author xiesu */
@SpringBootApplication
//@ComponentScan("org.corbin")
//@EnableJpaRepositories(basePackages = {"org.corbin.oauth.server.repository"})
public class OauthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(OauthServerApplication.class, args);
  }
}
