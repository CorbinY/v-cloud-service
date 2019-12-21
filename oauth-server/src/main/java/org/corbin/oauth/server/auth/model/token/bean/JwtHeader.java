package org.corbin.oauth.server.auth.model.token.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.Setter;
import org.corbin.oauth.server.auth.config.ECDSA256KeyStoreInit;
import org.corbin.tools.common.context.SpringBeanContextAware;
import org.corbin.tools.common.util.IdHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * token的header,可额外添加参数
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-13
 */
public class JwtHeader {
  /**
   * token的签名算法<br>
   * {@link Algorithm#getName()}
   */
  @JSONField(serialize = false)
  @Getter
  private Algorithm algorithm;

  /** 使用指定的算法 */
  public JwtHeader(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  /** 默认使用ecdsa256加密算法 */
  public JwtHeader() {
    this.algorithm =
        Algorithm.ECDSA256(
            ECDSA256AlgorithmInstance.BUILDER.ecPublicKey,
            ECDSA256AlgorithmInstance.BUILDER.ecPrivateKey);
  }

  private static class ECDSA256AlgorithmInstance {
    private static final Builder BUILDER = new Builder(loadEcPublicKey(), loadEcPrivateKey());

    private static class Builder {
      private ECPrivateKey ecPrivateKey;
      private ECPublicKey ecPublicKey;

      private Builder(ECPublicKey ecPublicKey, ECPrivateKey ecPrivateKey) {
        // 初始化变量
        this.ecPublicKey = ecPublicKey;
        this.ecPrivateKey = ecPrivateKey;
      }
    }
  }

  /** 加载默认的ECPublicKey */
  private static ECPublicKey loadEcPublicKey() {
    ClassPathResource classPathResource = new ClassPathResource("ECDSA256_PUB_KEY.keystore");
    InputStream inputStream;
    ObjectInputStream in;
    Object readObject = null;
    try {
      inputStream = classPathResource.getInputStream();
      in = new ObjectInputStream(inputStream);
      readObject = in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return (ECPublicKey) readObject;
  }

  /** 加载默认的ECPrivateKey */
  private static ECPrivateKey loadEcPrivateKey() {
    ClassPathResource classPathResource = new ClassPathResource("ECDSA256_PRI_KEY.keystore");
    InputStream inputStream;
    Object readObject = null;
    try {
      inputStream = classPathResource.getInputStream();
      ObjectInputStream in = new ObjectInputStream(inputStream);
      readObject = in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return (ECPrivateKey) readObject;
  }

  /**
   * 获取默认的ECPrivateKey
   *
   * @author xiesu / Corbin
   * @date 19-12-17
   */
  public static ECPrivateKey getDefaultECPrivateKey() {
    return ECDSA256AlgorithmInstance.BUILDER.ecPrivateKey;
  }

  /**
   * 获取默认ECPublicKey
   *
   * @author xiesu / Corbin
   * @date 19-12-17
   */
  public static ECPublicKey getDefaultECPublicKey() {
    return ECDSA256AlgorithmInstance.BUILDER.ecPublicKey;
  }

  /**
   * 获取默认加密算法
   *
   * @author xiesu / Corbin
   * @date 19-12-17
   */
  public static Algorithm getDefaultAlgorithm() {
    return Algorithm.ECDSA256(getDefaultECPublicKey(), getDefaultECPrivateKey());
  }
}
