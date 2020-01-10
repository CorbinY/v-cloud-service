package org.corbin.oauth.server.auth.domain;

import lombok.Builder;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-25
 */
@Setter
@Builder
public class AuthUser implements UserDetails {

  /** 认证id,使用认证账号唯一id标识 */
  private String authId;
  /** 用户进行表单登录时采用的登录密码 */
  private String password;

  private List<AuthorUserRole> authorities;


  /**
   * Returns the authorities granted to the user. Cannot return <code>null</code>.
   *
   * @return the authorities, sorted by natural key (never <code>null</code>)
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  /**
   * Returns the password used to authenticate the user.
   *
   * @return the password
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Returns the username used to authenticate the user. Cannot return <code>null</code>.
   *
   * @return the username (never <code>null</code>)
   */
  @Override
  public String getUsername() {
    return authId;
  }

  /**
   * Indicates whether the user's account has expired. An expired account cannot be authenticated.
   *
   * @return <code>true</code> if the user's account is valid (ie non-expired), <code>false</code>
   *     if no longer valid (ie expired)
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
   *
   * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Indicates whether the user's credentials (password) has expired. Expired credentials prevent
   * authentication.
   *
   * @return <code>true</code> if the user's credentials are valid (ie non-expired), <code>false
   *     </code> if no longer valid (ie expired)
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
   *
   * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

  /** 获取认证id */
  public String getAuthId() {
    return authId;
  }
}
