package org.corbin.fastdfsserver.repository;

import org.corbin.vcloud.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** @author xiesu */
public interface UserRepository extends MongoRepository<User, String> {

  User findByAccount(String account);
}
