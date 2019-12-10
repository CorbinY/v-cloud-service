package org.corbin.oauth.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.corbin.basic.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@Getter
@Setter
@Entity
@Table(
    name = "account",
    indexes = {
      @Index(columnList = "accountId", unique = true),
      @Index(columnList = "accountMail", unique = true),
      @Index(columnList = "accountTel", unique = true)
    })
public class Account extends BaseEntity implements Serializable {

  /** 账号id */
  @Column(unique = true, length = 25)
  protected String accountId;

  /** 允许登录的邮箱账号 */
  @Column(unique = true, length = 50)
  protected String accountMail;

  /** 允许登录的,手机号账号 */
  @Column(unique = true, length = 11)
  protected String accountTel;

  /** 登录密码 */
  protected String md5Pwd;

  /** 辅助验证邮箱 */
  @Column(length = 50)
  protected String assistVerificationMail;
}
