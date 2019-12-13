package org.corbin.tools.common.util;

import java.util.*;

/**
 * 生成含有年月日时间顺序的唯一id,
 *
 * <p>该方法生成id 可以在 <i>9999年12月12日23时59分59秒前</i> 可用<br>
 * 生成id格式 yyyy_MM_dd_tttttttt_ww_DD_RRRR<br>
 *
 * <p>yyyy四位表示年份<br>
 * MM两位表示当年月份<br>
 * dd表示当月天数<br>
 * tttttttt表示当前时间距当日凌晨的时间(单位:ms)<br>
 * ww 表示当前机器id,经过自定义hash散列<br>
 * DD 表示当前数据中心id,经过自定义hash散列<br>
 * RRRR表示毫秒内溢序的数字排列
 *
 * <p>数据示例:201912126095315703030053 <br>
 * </>表示2019年12月12日 16:58:ss.mmm 机器id=3 数据中心id=3 同一毫秒内溢序的第53个不重复id
 *
 * <p>飘雪算法,uuid算法,本算法生成id效率比较: <br>
 * 以单线程生成70万id为例: <br>
 * 飘雪算法需要1715ms左右的时间 <br>
 * 本算法需要2197ms左右的时间 <br>
 * uuid算法需要10433ms左右的时间
 *
 * <p>uuid无序且效率低,有极小可能重复 <br>
 * </>飘雪算法整体上按照时间顺序排序,可以保证在分布式架构上不重复且按照时间排序,但不适宜人工阅读 <br>
 * 本算法按照时间排序,可以保证在分布式架构上不重复且按照时间排序,人工较容易分辨id产生时间
 *
 * <p>使用时需要保证该多次使用的该类对象为同一个,或使用单例,否则无法判断时间戳的顺序
 *
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-11
 */
class SequenceIdUtil extends AbstractTimestampUtil {
  /** 上次生成的时间戳 */
  private long lastTimestamp;

  /** 散列后的集群的机器id 00-99 */
  private final StringBuilder workerIdBuilder;
  /** hash散列后的集群的数据中心id 00-99 */
  private final StringBuilder datacenterIdBuilder;
  /** 系统使用的calender对象 */
  private final Calendar calendar;
  /** 毫秒内序列 */
  private int millisecondSequence;

  /** 自定义workid hash散列码 */
  private final Map<Character, Character> workererIdHash =
      new HashMap<Character, Character>(10) {
        {
          put('1', '4');
          put('2', '6');
          put('3', '0');
          put('4', '3');
          put('5', '8');
          put('6', '2');
          put('7', '5');
          put('8', '7');
          put('9', '1');
          put('0', '9');
        }
      };

  /** 自定义datacennterId hash散列码 */
  private final Map<Character, Character> datacenterIdHash =
      new HashMap<Character, Character>(10) {
        {
          put('8', '4');
          put('9', '6');
          put('2', '0');
          put('7', '3');
          put('0', '8');
          put('1', '2');
          put('5', '5');
          put('6', '7');
          put('3', '1');
          put('4', '9');
        }
      };

  {
    this.lastTimestamp = -1L;
    this.millisecondSequence = 0;
    this.calendar = new GregorianCalendar();
  }

  SequenceIdUtil() {
    this(0, 0);
  }

  /**
   * @param workerId 参与集群的机器id 0-99
   * @param datacenterId 参与集群的数据中心id 0-99
   */
  SequenceIdUtil(int workerId, int datacenterId) {
    // 处理机器id
    int minWorkId = 0;
    int maxWorkId = 99;
    if (workerId > maxWorkId || workerId < minWorkId) {
      throw new IllegalArgumentException(
          String.format("worker Id can't be greater than %d or less than 0", maxWorkId));
    }
    this.workerIdBuilder = createWorkerIdBuilder(workerId);

    // 处理数据中心id
    int minDatacenterId = 0;
    int maxDatacenterId = 99;
    if (datacenterId > maxDatacenterId || datacenterId < minDatacenterId) {
      throw new IllegalArgumentException(
          String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.datacenterIdBuilder = createDatacenterIdBuilder(datacenterId);
  }

  /** 获取下一个id */
  synchronized String nextId() {
    // 毫秒内序列-最小溢序
    int minMillisecondSequence = 0;
    // 毫秒内序列- 最大溢序
    int maxMillisecondSequence = 9999;

    long timestamp = timeGen();
    // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
    if (timestamp < lastTimestamp) {
      throw new RuntimeException(
          String.format(
              "Clock moved backwards.  Refusing to generate id for %d milliseconds",
              lastTimestamp - timestamp));
    }
    // 如果是同一时间生成的则进行毫秒内序列
    if (timestamp == lastTimestamp) {
      millisecondSequence++;
      // 毫秒内序列溢出,则获取下一时间戳
      if ((millisecondSequence) > maxMillisecondSequence) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      millisecondSequence = minMillisecondSequence;
    }
    // 上次生成ID的时间截
    lastTimestamp = timestamp;

    return new String(buildSequenceId(timestamp, millisecondSequence));
  }

  /**
   * 构造sequenceId
   *
   * @param timestamp 时间戳
   */
  private StringBuilder buildSequenceId(long timestamp, long millisecondSequence) {
    // 重置日历
    calendar.setTimeInMillis(timestamp);

    // 返回构建的id
    return createYearBuilder(calendar)
        .append(createMonthBuilder(calendar))
        .append(createDayBuilder(calendar))
        .append(createTimeBuilder(timestamp))
        .append(workerIdBuilder)
        .append(datacenterIdBuilder)
        .append(createMilliSecondSequenceBuilder(millisecondSequence));
  }

  private StringBuilder createYearBuilder(Calendar calendar) {
    // 获取年份
    return new StringBuilder().append(calendar.get(Calendar.YEAR));
  }

  private StringBuilder createMonthBuilder(Calendar calendar) {
    int monthBits = 2;
    // 月份
    StringBuilder monthBuilder = new StringBuilder().append((calendar.get(Calendar.MONTH)) + 1);
    // 月份只占2为
    if (monthBuilder.length() < monthBits) {
      monthBuilder.insert(0, '0');
    }
    return monthBuilder;
  }

  private StringBuilder createDayBuilder(Calendar calendar) {
    int dayBits = 2;
    // 天数
    StringBuilder dayBuilder = new StringBuilder().append(calendar.get(Calendar.DATE));
    // 天数只占两位
    if (dayBuilder.length() < dayBits) {
      dayBuilder.insert(0, '0');
    }
    return dayBuilder;
  }

  private StringBuilder createTimeBuilder(long timestamp) {
    // 当日所过时间毫秒数所占位数
    int msBits = 8;
    // 24小时毫秒数
    long dayMilliSecond = 86400_000L;
    // 北京时间时区偏移毫秒数
    long beijingTimeZoneOffsetMilliSecond = 28800_000L;
    // 计算从当日凌晨至现在经过的毫秒数
    long dayTime = ((timestamp + beijingTimeZoneOffsetMilliSecond) % dayMilliSecond);
    // 当前时间
    StringBuilder dayTimeBuilder = new StringBuilder().append(dayTime);
    // 今日经过的时间毫秒数占8位
    while (dayTimeBuilder.length() < msBits) {
      dayTimeBuilder.insert(0, '0');
    }
    return dayTimeBuilder;
  }

  private StringBuilder createMilliSecondSequenceBuilder(long millisecondSequence) {
    // 毫秒溢序所占位数
    int mssBits = 4;
    // 毫秒溢序数
    StringBuilder millisecondSequenceBuilder = new StringBuilder().append(millisecondSequence);
    // 毫秒内溢序占4位
    while (millisecondSequenceBuilder.length() < mssBits) {
      millisecondSequenceBuilder.insert(0, '0');
    }
    return millisecondSequenceBuilder;
  }

  private StringBuilder createWorkerIdBuilder(int workerId) {
    String ws = workerId < 10 ? "0" + workerId : String.valueOf(workerId);
    StringBuilder wb = new StringBuilder();
    for (char ch : ws.toCharArray()) {
      wb.append(workererIdHash.get(ch));
    }
    return wb;
  }

  private StringBuilder createDatacenterIdBuilder(int datacenterId) {
    String ds = datacenterId < 10 ? "0" + datacenterId : String.valueOf(datacenterId);
    StringBuilder db = new StringBuilder();
    for (char ch : ds.toCharArray()) {
      db.append(datacenterIdHash.get(ch));
    }
    return db;
  }

  public Map<Character, Character> getWorkererIdHash() {
    return workererIdHash;
  }

  public Map<Character, Character> getDatacenterIdHash() {
    return datacenterIdHash;
  }
}
