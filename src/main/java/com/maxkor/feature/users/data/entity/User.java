package com.maxkor.feature.users.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

  public static final String TABLE_NAME = "users";
  public static final byte UNDEFINED_ID = 0;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String name;

  @Column(name = "second_name")
  private String secondName;

  public User() {
  }

  public User(
      String name,
      String secondName
  ) {
    this.name = name;
    this.secondName = secondName;
  }

  public User(
      long id,
      String name,
      String secondName
  ) {
    this.id = id;
    this.name = name;
    this.secondName = secondName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  @Override
  public String toString() {
    return "User: id= %s, name= %s, secondName= %s"
        .formatted(id, name, secondName);
  }
}
