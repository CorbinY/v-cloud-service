package org.corbin.fastdfsserver.service.impl;

import org.corbin.vcloud.model.User;
import org.corbin.vcloud.repository.UserRepository;
import org.corbin.vcloud.service.UserService;
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
