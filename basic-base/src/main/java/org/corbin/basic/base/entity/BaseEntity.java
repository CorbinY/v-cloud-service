package org.corbin.basic.base.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends AbstractEntity implements Serializable {

  @CreatedDate
  @Column(nullable = false)
  protected Date createDate;

  @LastModifiedDate
  @Column(nullable = false)
  protected Date updateDate;

  @CreatedBy
  @Column(length = 25)
  protected String creator;

  @LastModifiedBy
  @Column(length = 25)
  protected String updator;
}
