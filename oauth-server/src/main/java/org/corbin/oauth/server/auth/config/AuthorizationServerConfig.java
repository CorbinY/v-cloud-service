package org.corbin.oauth.server.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-18
 */
@Configuration
// 认证服务器配置
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 客户端配置
     * <p>
     * 由于设置了BCryptPasswordEncoder编码格式,这里也需要进行编码<br>
     * {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder#matches(CharSequence, String)}
     * 此处强制检查使用了编码格式
     * </>
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("clientId")
                //由于设置了编码格式,这里也需要进行编码
                .secret(passwordEncoder.encode("secret"))
                // token 有效时长
                //        .accessTokenValiditySeconds(72000)
                // 刷新令牌
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("all")
                .redirectUris("http://localhost:9303/");
    }

    /**
     * token 生成处理:指定签名
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    /**
     * 配置token管理
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new InMemoryTokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()") // 允许所有人请求令牌
                .checkTokenAccess("isAuthenticated()") // 已验证的客户端才能请求check_token端点
                .allowFormAuthenticationForClients();
    }
}
