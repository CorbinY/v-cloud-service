package org.corbin.fdfs.server.controller;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/")
public class Test {
  private final StorageClient storageClient;

  public Test(StorageClient storageClient) {
    this.storageClient = storageClient;
  }

  @GetMapping("/test-upload")
  public String bnji() throws IOException, MyException {

    String filename = "/home/xiesu/Pictures/123.jpg";
    NameValuePair[] nvp = new NameValuePair[] {new NameValuePair("12345", "12345-")};
    String[] fileId = storageClient.upload_file(filename, null, nvp);

    Arrays.asList(fileId).forEach(System.out::println);
    return "123333";
  }

  @GetMapping("/test-download")
  public String fnfrwg() throws IOException, MyException {
    storageClient.download_file(
        "group1", "M00/00/00/wKhTA13gjSiAcXFvAAAN6h8tr4g133.jpg", "/home/xiesu/Desktop/1.jpg");
    return "vgl;.'/";
  }
}
