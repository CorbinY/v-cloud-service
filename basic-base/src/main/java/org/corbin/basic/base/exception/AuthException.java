package org.corbin.basic.base.exception;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-17
 */
public class AuthException extends AbstractServiceException {
  protected AuthException(Integer errCode, String errMsg) {
    super(errCode, errMsg);
  }
}
