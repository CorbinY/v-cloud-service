package org.corbin.fastdfsserver.controller;

import org.corbin.vcloud.response.ResponseResult;
import org.corbin.vcloud.response.ResponseStatusEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 2019/11/29
 */
@RestController
@RequestMapping("/auth")
public class UserController {

    @PostMapping("/register")
  public ResponseResult register(
      @RequestParam(name = "account_id") String account, @RequestParam(name = "pwd") String pwd) {
    return ResponseResult.newInstance(ResponseStatusEnum.success_non_result);

  }
}
