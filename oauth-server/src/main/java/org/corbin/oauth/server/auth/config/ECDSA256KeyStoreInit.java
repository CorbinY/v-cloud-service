package org.corbin.oauth.server.auth.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * 初始化生成ECDSA256的 ECPublicKey && ECPrivateKey <br>
 * 程序仅需手动运行一次,每次生成的key不同,会覆盖掉前一次的key
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-16
 */
public class ECDSA256KeyStoreInit {
  private volatile KeyPair keyPair;

  private ECDSA256KeyStoreInit() {}

  private KeyPair getKeyPair() throws NoSuchAlgorithmException {

    if (keyPair == null) {
      synchronized (this) {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(256);
        keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
      }
    } else {
      return keyPair;
    }
  }

  private ECPublicKey generateECPublicKey() throws NoSuchAlgorithmException {
    return (ECPublicKey) getKeyPair().getPublic();
  }

  private ECPrivateKey generateECPrivateKey() throws NoSuchAlgorithmException {
    return (ECPrivateKey) getKeyPair().getPrivate();
  }

  /**
   * 此方法用于生成 ECDSA256加密算法的公钥密钥
   *
   * @param args
   * @throws NoSuchAlgorithmException
   * @throws IOException
   */
  public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
    ECDSA256KeyStoreInit init = new ECDSA256KeyStoreInit();
    ECPrivateKey ecPrivateKey = init.generateECPrivateKey();
    ECPublicKey ecPublicKey = init.generateECPublicKey();

    ObjectOutputStream pubOut =
        new ObjectOutputStream(
            new FileOutputStream(
                new File("oauth-server/src/main/resources/ECDSA256_PUB_KEY.keystore")));
    pubOut.writeObject(ecPublicKey);
    pubOut.flush();
    pubOut.close();

    ObjectOutputStream priOut =
        new ObjectOutputStream(
            new FileOutputStream(
                new File("oauth-server/src/main/resources/ECDSA256_PRI_KEY.keystore")));
    priOut.writeObject(ecPrivateKey);
    priOut.flush();
    priOut.close();
  }
}
