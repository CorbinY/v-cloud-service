package org.corbin.oauth.server.auth.service;

import org.corbin.oauth.server.auth.domain.AuthUser;
import org.corbin.oauth.server.auth.domain.AuthorUserRole;
import org.corbin.oauth.server.entity.Account;
import org.corbin.oauth.server.entity.Role;
import org.corbin.oauth.server.repository.AccountRepository;
import org.corbin.tools.common.util.PatternUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public AuthUser loadAuthUserAccount(@NonNull String emailOrTel) {
        Assert.notNull(emailOrTel, "登录账号不能为空");
        boolean isMail = PatternUtil.isMail(emailOrTel);
        Account account = null;
        // 邮箱登录
        if (isMail) {
            account = accountRepository.findByAccountMail(emailOrTel).orElse(null);
        }

        // 手机号登录
        boolean isTel = PatternUtil.isTel(emailOrTel);
        if (isTel) {
            account = accountRepository.findByAccountTel(emailOrTel).orElse(null);
        }
        if (Objects.isNull(account)) {
            return null;
        }

        // 转换为userDetail的实现类
        // 获取角色
        List<Role> roleList = account.getAuthorities();
        if (Objects.nonNull(roleList) && !roleList.isEmpty()) {
            AuthUser user =
                    AuthUser.builder().authId(account.getAccountId()).password(account.getMd5Pwd()).build();

            List<AuthorUserRole> authorUserRoleList =
                    roleList.stream()
                            .map(ele -> AuthorUserRole.builder().authority(ele.getAuthority()).build())
                            .collect(Collectors.toList());
            // 写入角色
            user.setAuthorities(authorUserRoleList);
            return user;
        }

        // 其他方式不支持
        return null;
    }
}
