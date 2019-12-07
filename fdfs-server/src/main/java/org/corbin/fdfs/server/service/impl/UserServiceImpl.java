package org.corbin.fdfs.server.service.impl;

import org.corbin.fdfs.server.model.User;
import org.corbin.fdfs.server.repository.UserRepository;
import org.corbin.fdfs.server.service.UserService;
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
