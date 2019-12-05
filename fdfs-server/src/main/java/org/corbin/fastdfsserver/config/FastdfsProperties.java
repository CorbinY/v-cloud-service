package org.corbin.fastdfsserver.config;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/** @author xiesu */
@Configuration("fastdfsConfig")
@Getter
@PropertySource(value = "fastdfs-client.properties")
public class FastdfsProperties {

  @Value("${fastdfs.connect_timeout_in_seconds}")
  @JSONField(name = "fastdfs.connect_timeout_in_seconds")
  private Integer connectTimeOut;

  @Value("${fastdfs.network_timeout_in_seconds}")
  @JSONField(name = "fastdfs.network_timeout_in_seconds")
  private Integer networkTimeOut;

  @Value("${fastdfs.charset}")
  @JSONField(name = "fastdfs.charset")
  private String charset;

  @Value("${fastdfs.http_tracker_http_port}")
  @JSONField(name = "fastdfs.http_tracker_http_port")
  private Integer httpTrackerPort;

  @Value("${fastdfs.http_anti_steal_token}")
  @JSONField(name = "fastdfs.http_anti_steal_token")
  private Boolean httpAntiStealToken;

  @Value("${fastdfs.http_secret_key}")
  @JSONField(name = "fastdfs.http_secret_key")
  private String httpSecretToken;

  @Value("${fastdfs.tracker_servers}")
  @JSONField(name = "fastdfs.tracker_servers")
  private String trackerServers;

  public FastdfsProperties() {}

  public FastdfsProperties(FastdfsProperties properties) {
    this.charset = properties.getCharset();
    this.connectTimeOut = properties.getConnectTimeOut();
    this.httpAntiStealToken = properties.getHttpAntiStealToken();
    this.httpSecretToken = properties.getHttpSecretToken();
    this.httpTrackerPort = properties.getHttpTrackerPort();
    this.networkTimeOut = properties.getNetworkTimeOut();
    this.trackerServers = properties.getTrackerServers();
  }
}
