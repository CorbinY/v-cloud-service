package org.corbin.tools.common.util;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/** @author xiesu */
public class PatternUtil {

  /**
   * 是否是邮箱
   *
   * @param mailString 待匹配邮箱字符串
   * @author xiesu / Corbin
   * @date 19-12-6
   */
  public static boolean isMail(String mailString) throws PatternSyntaxException {
    if (Objects.isNull(mailString)) {
      return false;
    }
    String regExp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    return isMatched(mailString, regExp);
  }

  /**
   * 判断是否是正确的手机号
   *
   * @param telString 待匹配的手机号字符串
   * @author xiesu / Corbin
   * @date 19-12-6
   */
  public static boolean isTel(String telString) {
    if (Objects.isNull(telString)) {
      return false;
    }

    String regExp = "^1(3|4|5|7|8)\\d{9}$";
    return isMatched(telString, regExp);
  }

  /**
   * 返回给定的字符串是否可以被正则式匹配
   *
   * @param matchString 待匹配字符串
   * @param regExp 匹配正则表达式
   */
  public static boolean isMatched(String matchString, String regExp) {
    Objects.requireNonNull(regExp, "正则匹配表达式不能为空");
    Objects.requireNonNull(matchString, "待匹配字符串不能为空");
    return Pattern.compile(regExp).matcher(matchString).matches();
  }
}
