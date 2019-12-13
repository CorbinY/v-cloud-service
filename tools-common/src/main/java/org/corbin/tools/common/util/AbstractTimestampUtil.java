package org.corbin.tools.common.util;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-11
 */
abstract class AbstractTimestampUtil {

  /**
   * 阻塞到下一个毫秒，直到获得新的时间戳
   *
   * @param lastTimestamp 上次生成ID的时间截
   * @return 当前时间戳
   */
  long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  /**
   * 返回以毫秒为单位的当前时间
   *
   * @return 当前时间(毫秒)
   */
  long timeGen() {
    return System.currentTimeMillis();
  }
}
