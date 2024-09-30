package com.maxkor.users.service;

import com.maxkor.users.dao.UserDao;
import com.maxkor.users.model.User;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }


  @Transactional
  @Override
  public void upsert(User user) {
    if (user.getId() == User.UNDEFINED_ID) {
      userDao.save(user);
    } else {
      userDao.update(user);
    }
  }

  @Transactional
  @Override
  public void removeById(long id) {
    userDao.removeById(id);
  }

  @Transactional
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