package org.corbin.basic.base.exception;

/**
 * 基础业务异常
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-17
 */
public abstract class AbstractServiceException extends Exception {
  private Integer errCode;
  private String errMsg;

  protected AbstractServiceException(Integer errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }
}
