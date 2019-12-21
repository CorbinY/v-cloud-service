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
}
