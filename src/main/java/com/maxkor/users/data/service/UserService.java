package com.maxkor.users.data.service;

import com.maxkor.users.data.model.User;
import java.util.List;

public interface UserService {

  void upsert(User user);

  void removeById(long id);

  void removeAll();

  List<User> getAll();
}
