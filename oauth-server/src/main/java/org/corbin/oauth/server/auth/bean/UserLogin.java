package org.corbin.oauth.server.auth.bean;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-6
 */
@Getter
@Setter
@Builder
public class UserLogin implements UserDetails, Serializable {

  //  private String userId;
  private String username;
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Lists.newArrayList();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  //  public String getUserId() {
  //    return userId;
  //  }
}
