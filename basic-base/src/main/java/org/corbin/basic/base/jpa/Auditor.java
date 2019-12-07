package org.corbin.basic.base.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * 实现jpa的审计功能
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@Configuration
public class Auditor implements AuditorAware<String> {

  /** 获取当前创建或修改的用户 */
  @Override
  public Optional<String> getCurrentAuditor() {

    try {
      UserDetails user =
          (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return Optional.ofNullable(user.getUsername());
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
