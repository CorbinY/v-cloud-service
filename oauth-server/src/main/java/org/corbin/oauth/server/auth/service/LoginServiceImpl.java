package org.corbin.oauth.server.auth.service;

import org.corbin.oauth.server.auth.bean.UserLogin;
import org.corbin.oauth.server.entity.Account;
import org.corbin.oauth.server.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-6
 */
@Service
public class LoginServiceImpl implements UserDetailsService {
  @Autowired private AccountServiceImpl accountService;

  /**
   * 获取用户的登录信息
   *
   * @param username 此处受支持的为邮箱或手机号(登录账号)
   * @throws UsernameNotFoundException 未找到抛异常
   * @author xiesu / Corbin
   * @date 19-12-6
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountService.findLoginAccount(username);
    if (Objects.isNull(account)) {
      throw new UsernameNotFoundException("用户不存在");
    }

    // 返回的userDetails实现类中username为账户的唯一标识->accountId
    return UserLogin.builder()
        .password(account.getMd5Pwd())
        .username(account.getAccountId())
        .build();
  }
}
