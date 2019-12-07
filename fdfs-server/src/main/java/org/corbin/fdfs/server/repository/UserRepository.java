package org.corbin.fdfs.server.repository;

import org.corbin.fdfs.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/** @author xiesu */
public interface UserRepository extends MongoRepository<User, String> {

  User findByAccount(String account);
}
