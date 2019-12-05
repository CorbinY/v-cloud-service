package org.corbin.fastdfsserver.repository;

import org.corbin.fastdfsserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/** @author xiesu */
public interface UserRepository extends MongoRepository<User, String> {

  User findByAccount(String account);
}
