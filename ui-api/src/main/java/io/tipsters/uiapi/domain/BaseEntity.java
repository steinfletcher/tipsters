package io.tipsters.uiapi.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable {
  @Id
  @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
  @GeneratedValue(generator = "uuid-gen")
  @Type(type = "pg-uuid")
  private UUID id;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
