package com.maxkor.users.service;

import com.maxkor.users.model.User;
import java.util.List;

public interface UserService {

  void upsert(User user);

  void removeById(long id);

  void removeAll();

  List<User> getAll();
}
