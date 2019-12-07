package org.corbin.oauth.server.auth.config;

import org.corbin.oauth.server.auth.handler.LoginSuccessHander;
import org.corbin.oauth.server.auth.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-6
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private LoginServiceImpl userLoginService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userLoginService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.formLogin().successHandler(new LoginSuccessHander());
//    http.authorizeRequests().anyRequest().fullyAuthenticated();
  }
}
