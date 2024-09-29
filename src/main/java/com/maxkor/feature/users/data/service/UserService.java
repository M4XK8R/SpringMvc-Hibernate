package com.maxkor.feature.users.data.service;

import com.maxkor.feature.users.data.entity.User;
import java.util.List;

public interface UserService {

  void upsert(User user);

  void removeById(long id);

  void removeAll();

  List<User> getAll();
}
