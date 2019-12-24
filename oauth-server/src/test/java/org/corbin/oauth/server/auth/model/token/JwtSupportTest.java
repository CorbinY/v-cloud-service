package org.corbin.oauth.server.auth.model.token;

import org.corbin.oauth.server.auth.model.token.bean.AccessTokenPayload;
import org.corbin.oauth.server.auth.model.token.bean.JwtHeader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class JwtSupportTest {

  @Test
  public void teat() {
    AccessTokenPayload payload = new AccessTokenPayload();
    payload.setAccountId("1234567890666-");
    String token = JwtSupport.buildToken(new JwtHeader(), payload);
    System.out.println(token);
    // eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJ1bmlvbmlkIjoiMTIzNDU2Nzg5MC0ifQ.iT1Oa66nF6cLU1mWgZIgmQi-5aDz-G1M0353ZJABWIGiFTfiEL2baEBuzrTP6YNqP-6TCxpDqe_DRmubQQr_zQ
  }

//  @Test
//  public void w() {
//    DecodedJWT decodedJWT =
//        JwtSupport.decodeLegalTokenPayload(
//            "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJ1bmlvbmlkIjoiMTIzNDU2Nzg5MDY2Ni0ifQ.iT1Oa66nF6cLU1mWgZIgmQi-5aDz-G1M0353ZJABWIGiFTfiEL2baEBuzrTP6YNqP-6TCxpDqe_DRmubQQr_zQ");
//
//    System.out.println(JSON.toJSONString(decodedJWT));
//  }
}
