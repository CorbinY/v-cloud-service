package org.corbin.basic.base.entity;

import lombok.Getter;
import lombok.Setter;
import org.corbin.tools.common.util.SnowFlakeIdHelper;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  /** 定义主键 */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  /**
   * 飘雪算法根据时间戳生成分布式唯一id<br>
   * 返回long型熟虑<br>
   * 必要时可以重写
   *
   * @author xiesu / Corbin
   * @date 19-12-5
   */
  public static Long createUniqueLongId() {
    return SnowFlakeIdHelper.nextId2Long();
  }

  /**
   * 建议使用飘雪算法根据时间戳生成分布式唯一id<br>
   * 返回String型数据,可以作为业务id唯一标示<br>
   * 建议子类根据自己的业务重写,添加头标示
   *
   * @author xiesu / Corbin
   * @date 19-12-5
   */
  public static String createUniqueStringId() {
    return SnowFlakeIdHelper.nextId2String();
  }
}
