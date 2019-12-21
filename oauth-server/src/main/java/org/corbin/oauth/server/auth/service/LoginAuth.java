package org.corbin.oauth.server.auth.service;

import org.corbin.oauth.server.entity.Account;
import org.corbin.oauth.server.repository.AccountRepository;
import org.corbin.tools.common.util.PatternUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-10
 */
@Service
public class LoginAuth {
  private final AccountRepository accountRepository;

  LoginAuth(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  /**
   * 加载待验证用户的账户
   *
   * @param emailOrTel 用户的账号
   * @return 账号信息
   */
  public Account loadAuthUserAccount(@NonNull String emailOrTel) {
    Assert.notNull(emailOrTel, "登录账号不能为空");
    boolean isMail = PatternUtil.isMail(emailOrTel);
    // 邮箱登录
    if (isMail) {
      return accountRepository.findByAccountMail(emailOrTel).orElse(null);
    }

    // 手机号登录
    boolean isTel = PatternUtil.isTel(emailOrTel);
    if (isTel) {
      return accountRepository.findByAccountTel(emailOrTel).orElse(null);
    }
    // 其他方式不支持
    return null;
  }
}
