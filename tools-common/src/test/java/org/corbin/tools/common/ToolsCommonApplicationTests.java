package org.corbin.tools.common;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolsCommonApplicationTests {

  @Test
  public void ftgh() {
    T t = new T();
    t.setId("wdq");
    System.out.println(t.getId());
  }
}
