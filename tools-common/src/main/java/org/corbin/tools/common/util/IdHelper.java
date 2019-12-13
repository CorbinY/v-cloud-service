package org.corbin.tools.common.util;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-11
 */
public class IdHelper {

  /** 产生下一个飘雪算法ID */
  public static Long nextLongSnowFlackId() {
    return SnowFlakeUtilInstance.SNOW_FLAKE.nextId();
  }
  /** 产生下一个飘雪算法ID */
  public static String nextStringSnowFlackId() {
    return String.valueOf(SnowFlakeUtilInstance.SNOW_FLAKE.nextId());
  }
  /** 产生下一个UUID */
  public static String nextUuid() {
    return UuidUtilInstance.UUID_UTIL.uuidString();
  }

  /** 产生下一个sequenceID */
  public static String nextSequenceId() {
    return SequenceIdInstance.SEQUENCE_ID_UTIL.nextId();
  }

  private static class SnowFlakeUtilInstance {
    private static final SnowFlakeIdUtil SNOW_FLAKE = new SnowFlakeIdUtil(9, 5);
  }

  private static class UuidUtilInstance {
    private static final UuidUtil UUID_UTIL = new UuidUtil();
  }

  private static class SequenceIdInstance {
    private static final SequenceIdUtil SEQUENCE_ID_UTIL = new SequenceIdUtil(9, 5);
  }
}
