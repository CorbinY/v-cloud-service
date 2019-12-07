package org.corbin.fdfs.server.response;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 2019/11/29
 */
public enum  ResponseStatusEnum {
    /**
     * do option is success and the result non null
     */
    success(200,"操作成功"),
    /**
     * do option is success but the result is null
     */
    success_non_result(-200,"操作成功，但结果为空");





  /** 返回结果状态码 */
  private Integer code;
  /** 返回基础消息 */
  private String baseMsg;

  ResponseStatusEnum(Integer code, String baseMsg) {
    this.code = code;
    this.baseMsg = baseMsg;
  }

  public Integer code() {
    return this.code;
  }
  public String baseMsg() {
    return this.baseMsg;
  }
}
