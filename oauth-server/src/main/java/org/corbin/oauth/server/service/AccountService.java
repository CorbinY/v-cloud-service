package org.corbin.oauth.server.service;

import org.corbin.oauth.server.entity.Account;
import org.springframework.lang.NonNull;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
public interface AccountService {

  /**
   * @param emailOrTel 账户登录使用的邮箱或手机号
   * @author xiesu / Corbin
   * @date 19-12-5
   */
  Account findLoginAccount(@NonNull String emailOrTel);
}
