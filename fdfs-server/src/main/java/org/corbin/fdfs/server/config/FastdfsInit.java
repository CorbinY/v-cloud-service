package org.corbin.fdfs.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Component
public class FastdfsInit {
  private final FastdfsProperties fastdfsConfig;

  public FastdfsInit(@Qualifier(value = "fastdfsConfig") FastdfsProperties fastdfsConfig) {
    this.fastdfsConfig = fastdfsConfig;
  }

  @SuppressWarnings("unchecked")
  public Properties getFastdfsProperties() {
    Properties properties = new Properties();

    FastdfsProperties p = new FastdfsProperties(fastdfsConfig);
    Map<String, Object> params = JSONObject.parseObject(JSON.toJSONString(p), Map.class);
    params.forEach(properties::put);
    // 初始化fastdfs配置
    return properties;
  }

  @Bean(value = "trackerServer")
  public TrackerServer getTrackerServer() {

    TrackerServer trackerServer = null;
    try {
      // 初始化配置文件
      ClientGlobal.initByProperties(getFastdfsProperties());
      trackerServer = new TrackerClient().getConnection();
    } catch (MyException e) {
      e.printStackTrace();
    } catch (IOException e) {
      log.info("初始化trackServer失败");
      e.printStackTrace();
    }
    return trackerServer;
  }

  @Bean(value = "storageClient")
  public StorageClient getStorageClient(TrackerServer trackerServer) {
    return new StorageClient(trackerServer, null);
  }
}
