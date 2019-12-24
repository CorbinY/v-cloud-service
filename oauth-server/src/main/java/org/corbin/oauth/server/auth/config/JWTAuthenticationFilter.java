package org.corbin.oauth.server.auth.config;

import com.google.common.collect.Lists;
import org.corbin.auth.token.JwtSupport;
import org.corbin.auth.token.payload.AbstractTokenPayload;
import org.corbin.oauth.server.entity.Account;
import org.corbin.oauth.server.entity.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xiesu
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader("token");
    /* token为null直接走登录的过滤器，不为空走下面 */
    if (token != null && token.trim().length() > 0) {
      AbstractTokenPayload payload = JwtSupport.decodeLegalTokenPayload(token);
      Account account = new Account();
      Role role = new Role();
      role.setAuthority("USER");
      role.setRoleId(1);
      account.setAuthorities(Lists.newArrayList(role));

      SecurityContextHolder.getContext()
          .setAuthentication(
              new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities()));
    }

    filterChain.doFilter(request, response);
  }
}
