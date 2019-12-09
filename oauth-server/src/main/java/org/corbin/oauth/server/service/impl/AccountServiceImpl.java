package org.corbin.oauth.server.service.impl;

import org.corbin.basic.base.service.impl.BaseServiceImpl;
import org.corbin.oauth.server.entity.Account;
import org.corbin.oauth.server.repository.AccountRepository;
import org.corbin.oauth.server.service.AccountService;
import org.corbin.tools.common.util.PatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService {
  private final AccountRepository accountRepository;

  AccountServiceImpl(AccountRepository accountRepository) {
    super(accountRepository);
    this.accountRepository = accountRepository;
  }

  @Override
  public Account findLoginAccount(@NonNull String emailOrTel) {
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
