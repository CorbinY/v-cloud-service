package org.corbin.oauth.server.entity;

import org.corbin.basic.base.entity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-19
 */
@Entity
@Table(name = "role_info")
public class Role extends BaseEntity implements GrantedAuthority, Serializable {

  @Column(name = "role_id")
  private Integer roleId;

  @Column(name = "role")
  private String authority;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private List<Account> accounts = new ArrayList<>();

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Override
  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }
}
