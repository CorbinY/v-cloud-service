package org.corbin.oauth.server.auth.config;

import com.alibaba.fastjson.JSON;
import org.corbin.oauth.server.auth.model.token.bean.JwtHeader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest() // 这里是启动类
class ECDSA256KeyStoreInitTest {

  @Test
  public void fghjk() {

    System.out.println(JSON.toJSONString(JwtHeader.getDefaultAlgorithm()));
    System.out.println(JSON.toJSONString(JwtHeader.getDefaultECPrivateKey()));
    System.out.println(JSON.toJSONString(JwtHeader.getDefaultECPublicKey()));
  }


}
