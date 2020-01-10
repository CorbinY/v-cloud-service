package org.corbin.oauth.server.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-26
 */
//@Configuration
public class JwtConfig {

//  @Bean
//  public TokenStore jwtTokenStore() {
//    return new JwtTokenStore(jwtAccessTokenConverter());
//  }

//  /**
//   * token 生成处理:指定签名
//   *
//   * @return
//   */
//  @Bean
//  public JwtAccessTokenConverter accessTokenConverter() {
//    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//    converter.setSigningKey("secret");
//    return converter;
//  }


}
