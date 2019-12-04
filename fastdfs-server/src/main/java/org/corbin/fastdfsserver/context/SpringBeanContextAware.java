package org.corbin.fastdfsserver.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * extends ApplicationObjectSupport 获取不到bean，改为实现ApplicationContextAware
 *
 * @author yin
 * @date 2019/07/24
 */
@Component
public class SpringBeanContextAware implements ApplicationContextAware {
  /**
   * 提供一个接口，获取容器中的Bean实例，根据名称获取
   *
   * @param beanName bean的名字
   * @return 返回bean
   */
  public static <T> T getBeanWithName(String beanName) {
    return Objects.requireNonNull(getBean(beanName));
  }

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(@NonNull ApplicationContext applicationContext)
      throws BeansException {
    SpringBeanContextAware.applicationContext = Objects.requireNonNull(applicationContext);
  }

  /**
   * 内部 获取bean 方法
   *
   * @param beanName
   * @param <T>
   * @return
   */
  @SuppressWarnings("unchecked")
  private static <T> T getBean(String beanName) {
    if (applicationContext.containsBean(beanName)) {
      return (T) applicationContext.getBean(beanName);
    } else {
      return null;
    }
  }
}
