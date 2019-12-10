package org.corbin.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/** @author xiesu */
@EnableJpaAuditing
@SpringBootApplication
public class OauthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(OauthServerApplication.class, args);
  }
}
