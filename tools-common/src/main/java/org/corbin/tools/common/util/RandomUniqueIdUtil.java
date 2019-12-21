package org.corbin.tools.common.util;

import java.util.Random;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-16
 */
public class RandomUniqueIdUtil {

  String nextId() {
    return String.valueOf(new Random().nextInt(100) + 100);
  }
}
