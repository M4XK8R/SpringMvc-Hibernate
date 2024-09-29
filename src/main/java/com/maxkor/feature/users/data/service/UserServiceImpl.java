package com.maxkor.feature.users.data.service;

import com.maxkor.feature.users.data.dao.UserDao;
import com.maxkor.feature.users.data.dao.UserDaoHibernateJpaImpl;
import com.maxkor.feature.users.data.entity.User;
import java.util.List;

public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl() {
    this.userDao = new UserDaoHibernateJpaImpl();
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

  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }
}