package org.corbin.tools.common.util;

import java.util.UUID;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-11
 */
class UuidUtil {

  /** 获取uuid,删除'-' */
  String uuidString() {
    return UUID.randomUUID().toString().replaceAll("-", "").trim();
  }
}
