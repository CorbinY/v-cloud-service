package org.corbin.auth.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.corbin.auth.token.payload.AbstractTokenPayload;
import org.corbin.auth.token.header.JwtHeader;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

/**
 * // // public static void main(String[] args) // throws NoSuchAlgorithmException,
 * InvalidKeySpecException, InvalidKeyException, // SignatureException { // String src = "Hello"; //
 * KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC"); //
 * keyPairGenerator.initialize(256); // KeyPair keyPair = keyPairGenerator.generateKeyPair(); //
 * ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic(); // ECPrivateKey ecPrivateKey =
 * (ECPrivateKey) keyPair.getPrivate();
 *
 * <p>// System.out.println(JSON.toJSONString(ecPublicKey)); //
 * System.out.println(JSON.toJSONString(ecPrivateKey));
 *
 * <p>// // 执行签名 // PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new //
 * PKCS8EncodedKeySpec(ecPrivateKey.getEncoded()); // KeyFactory keyFactory =
 * KeyFactory.getInstance("EC"); // PrivateKey privateKey =
 * keyFactory.generatePrivate(pkcs8EncodedKeySpec); // Signature signature =
 * Signature.getInstance("SHA1withECDSA"); // signature.initSign(privateKey); //
 * signature.update(src.getBytes()); // byte[] arr = signature.sign(); // System.out.println("jdk
 * ecdsa sign :" + new String(arr, StandardCharsets.UTF_8));
 *
 * <p>// // 验证签名 // X509EncodedKeySpec x509EncodedKeySpec = new
 * X509EncodedKeySpec(ecPublicKey.getEncoded()); // keyFactory = KeyFactory.getInstance("EC"); //
 * PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec); // signature =
 * Signature.getInstance("SHA1withECDSA"); // signature.initVerify(publicKey); //
 * signature.update(src.getBytes()); // boolean bool = signature.verify(arr); //
 * System.out.println("jdk ecdsa verify:" + bool);
 *
 * <p>// ECPublicKey ecPublicKey12 = new ECPublicKeyImpl("345".getBytes(StandardCharsets.UTF_8)); //
 * ECPrivateKey ecPrivateKey1 = new ECPrivateKeyImpl("123".getBytes(StandardCharsets.UTF_8)); //
 * JwtHeader header = new JwtHeader(Algorithm.ECDSA256(ecPublicKey, ecPrivateKey)); //
 * AccessTokenPayload tokenPayload = new AccessTokenPayload(); //
 * tokenPayload.setAccountId("hello"); // String token = JwtSupport.buildToken(header,
 * tokenPayload); // System.out.println(token); //
 *
 * <p>// }
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-13
 */
@Slf4j
public class JwtSupport {
  private JwtSupport() {}

  /**
   * 生成token
   *
   * @param jwtHeader header
   * @param tokenPayload payload
   */
  public static String buildToken(JwtHeader jwtHeader, AbstractTokenPayload tokenPayload) {
    Objects.requireNonNull(jwtHeader);
    Objects.requireNonNull(tokenPayload);
    // 加密算法
    Algorithm algorithm = jwtHeader.getAlgorithm();
    // 获取载体
    String payloadJson = JSON.toJSONString(tokenPayload);
    // 增加默认头
    Map<String, Object> headMap = JSONObject.parseObject(JSON.toJSONString(jwtHeader));
    headMap.put(PublicClaims.ALGORITHM, algorithm.getName());
    headMap.put(PublicClaims.TYPE, "JWT");
    String headerJson = JSON.toJSONString(headMap);
    // 编码
    //    String header =
    // Base64.encodeBase64URLSafeString(headerJson.getBytes(StandardCharsets.UTF_8));
    //    String payload =
    // Base64.encodeBase64URLSafeString(payloadJson.getBytes(StandardCharsets.UTF_8));

    String header =
        Base64.getUrlEncoder().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
    String payload =
        Base64.getUrlEncoder().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));

    // 签名
    byte[] signatureBytes =
        algorithm.sign(
            header.getBytes(StandardCharsets.UTF_8), payload.getBytes(StandardCharsets.UTF_8));
    String signature = Base64.getUrlEncoder().encodeToString((signatureBytes));
    return String.format("%s.%s.%s", header, payload, signature);
  }

  /**
   * 校验token
   *
   * @param token 待校验 token
   * @author xiesu / Corbin
   * @date 19-12-17
   */
  private static DecodedJWT verifier(String token) {
    DecodedJWT decodedJWT = null;
    try {
      decodedJWT = JWT.require(JwtHeader.getDefaultAlgorithm()).build().verify(token);
    } catch (Exception e) {
      log.error("Token被非法篡改");
    }
    return decodedJWT;
  }

  /**
   * 获取合法的token的有效信息负载(已校验token的合法性)
   *
   * @param token token
   * @author xiesu / Corbin
   * @date 19-12-19
   */
  public static JSONObject decodeLegalTokenPayload(String token) {
    DecodedJWT decodedjwt = verifier(token);
    String payload = decodedjwt.getPayload();

    byte[] payloadByte = Base64.getUrlDecoder().decode(payload);
    String payloadJsonString = new String(payloadByte);

    return JSON.parseObject(payloadJsonString);
  }
}
