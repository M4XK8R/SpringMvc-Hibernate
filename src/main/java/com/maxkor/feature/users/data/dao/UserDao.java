package com.maxkor.feature.users.data.dao;

import com.maxkor.feature.users.data.entity.User;
import java.util.List;

public interface UserDao {

  void createTable();

  void dropTable();

  void save(User user);

  void update(User user);

  void removeById(long id);

  List<User> getAll();

  void cleanTable();
}
