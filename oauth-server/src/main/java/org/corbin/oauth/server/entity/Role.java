package org.corbin.oauth.server.entity;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Entity
@Table(name = "role_info")
public class Role extends BaseEntity implements Serializable {

  @Column(name = "role_id")
  private Integer roleId;

  @Column(name = "role")
  private String authority;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private List<Account> accounts = new ArrayList<>();
}
