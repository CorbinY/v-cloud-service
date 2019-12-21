package org.corbin.oauth.server.auth.model.token.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础JWT 负载信息,<i>使用fastjson序列化时值,为空的字段不参与序列化</i>
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-11
 */
@Getter
@Setter
public abstract class AbstractTokenPayload {
  /** jti (JWT ID)：编号 */
  @JSONField(name = "jti")
  private String ticket;

  /** iss (issuer)：签发人 */
  @JSONField(name = "iss")
  private String issure;

  /** exp (expiration time)：过期时间 */
  @JSONField(name = "exp")
  private Date expirationTime;

  /** sub (subject)：主题 */
  @JSONField(name = "sub")
  private String subject;

  /** aud (audience)：受众 */
  @JSONField(name = "aud")
  private String audience;

  /** nbf (Not Before)：生效时间 */
  @JSONField(name = "nbf")
  private Date notBefore;

  /** iat (Issued At)：签发时间 */
  @JSONField(name = "iat")
  private Date issuedAt;

  /** 全局唯一 */
  @JSONField(name = "unionid")
  private String accountId;

  /** 创建ticket作为有效token的编号 */
  public abstract String createTicket();
}
