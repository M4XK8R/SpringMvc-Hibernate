package com.maxkor.users.data.dao;

import com.maxkor.users.data.model.User;
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
