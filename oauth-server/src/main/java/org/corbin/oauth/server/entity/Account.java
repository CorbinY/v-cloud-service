package org.corbin.oauth.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.corbin.basic.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@Getter
@Setter
@Entity
@Table(
    name = "account_info",
    indexes = {
      @Index(columnList = "account_id", unique = true),
      @Index(columnList = "account_mail", unique = true),
      @Index(columnList = "account_tel", unique = true)
    })
public class Account extends BaseEntity implements Serializable {

  /** 账号id */
  @Column(name = "account_id", unique = true, length = 25)
  protected String accountId;

  /** 允许登录的邮箱账号 */
  @Column(name = "account_mail", unique = true, length = 50)
  protected String accountMail;

  /** 允许登录的,手机号账号 */
  @Column(name = "account_tel", unique = true, length = 11)
  protected String accountTel;

  /** 登录密码 */
  protected String md5Pwd;

  /** 辅助验证邮箱 */
  @Column(length = 50)
  protected String assistVerificationMail;

  /** 角色列表 */
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
      name = "account_role",
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
      joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")})
  private List<Role> authorities = new ArrayList<>();
}
