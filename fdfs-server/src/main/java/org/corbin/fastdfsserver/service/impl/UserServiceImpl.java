package org.corbin.fastdfsserver.service.impl;

import org.corbin.fastdfsserver.model.User;
import org.corbin.fastdfsserver.repository.UserRepository;
import org.corbin.fastdfsserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
@Autowired private UserRepository userRepository;

    /**
     *
     * @param account
     * @return
     */
    public User findUserByAccount(String account){
        return userRepository.findByAccount(account);
    }


}
