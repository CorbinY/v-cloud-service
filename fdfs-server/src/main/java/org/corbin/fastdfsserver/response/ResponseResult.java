package org.corbin.fastdfsserver.response;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 2019/11/29
 */
@Getter
@Setter
public class ResponseResult implements Serializable {

  private Integer code;
  private String baseMsg;
  private String extraMsg;
  private Object result;

  public static ResponseResult newInstance(ResponseStatusEnum statusEnum, String extraMsg) {
    return new ResponseResult(statusEnum, extraMsg);
  }

  public static ResponseResult newInstance(ResponseStatusEnum statusEnum) {
    return new ResponseResult(statusEnum);
  }

  public static ResponseResult newInstance(ResponseStatusEnum statusEnum, Object result) {
    return new ResponseResult(statusEnum, result);
  }

  public static ResponseResult newInstance(
      ResponseStatusEnum statusEnum, String extraMsg, Object result) {
    return new ResponseResult(statusEnum, extraMsg, result);
  }

  private ResponseResult(ResponseStatusEnum statusEnum, String extraMsg, Object result) {
    if (ResponseStatusEnum.success.equals(statusEnum) && Objects.isNull(result)) {
      statusEnum = ResponseStatusEnum.success_non_result;
    }
    this.code = statusEnum.code();
    this.baseMsg = statusEnum.baseMsg();
    this.extraMsg = extraMsg;
    this.result = Objects.isNull(result) ? Maps.newHashMap() : result;
  }

  private ResponseResult(ResponseStatusEnum statusEnum) {
    this(statusEnum, null, null);
  }

  private ResponseResult(ResponseStatusEnum statusEnum, String extraMsg) {
    this(statusEnum, extraMsg, null);
  }

  private ResponseResult(ResponseStatusEnum statusEnum, Object result) {
    this(statusEnum, null, result);
  }
}
