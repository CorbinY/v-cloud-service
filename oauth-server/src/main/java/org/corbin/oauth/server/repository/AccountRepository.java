package org.corbin.oauth.server.repository;

import org.corbin.basic.base.repository.BaseRepository;
import org.corbin.oauth.server.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {

  Optional<Account> findByAccountId(String accountId);

  Optional<Account> findByAccountMail(String accountMail);

  Optional<Account> findByAccountTel(String accountTel);

  //  @Query(
  //      value =
  //          "select a from Account as a where  a.accountMail=?1 or a.accountTel=?1 and a.md5Pwd=?2
  // order by id limit 1")
  //  Account matchLoginAccount(String accountMailOrAccountTel, String md5Pwd);
}
