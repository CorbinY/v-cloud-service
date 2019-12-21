package org.corbin.oauth.server.auth.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-6
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @GetMapping("/get-pwd")
  public String testef() {
    String pwd = "123456";
    return new BCryptPasswordEncoder().encode(pwd);
  }

}
