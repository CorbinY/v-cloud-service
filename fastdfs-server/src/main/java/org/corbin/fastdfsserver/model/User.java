package org.corbin.fastdfsserver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/** @author xiesu */
@Getter
@Setter
@Document(value = "user_info")
public class User {

  @Id private String id;

  //  @Indexed(unique = true)
  @Field("account_id")
  private String account;

  @Field("pwd_md5")
  private String pwdMd5;
}
