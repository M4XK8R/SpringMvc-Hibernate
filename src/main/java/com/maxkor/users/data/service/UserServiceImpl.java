package com.maxkor.users.data.service;

import com.maxkor.users.data.dao.UserDao;
import com.maxkor.users.data.model.User;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }


  @Override
  public void upsert(User user) {
    if (user.getId() == User.UNDEFINED_ID) {
      userDao.save(user);
    } else {
      userDao.update(user);
    }
  }

  @Override
  public void removeById(long id) {
    userDao.removeById(id);
  }

  @Override
  public void removeAll() {
    userDao.cleanTable();
  }

  @Transactional(readOnly = true)
  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }
}