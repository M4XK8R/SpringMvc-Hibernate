package com.maxkor.users.data.dao;

import com.maxkor.users.data.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoHibernateJpaImpl implements UserDao {

  @PersistenceContext
  public EntityManager entityManager;

  @Override
  public void createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS users (" +
        "id BIGINT NOT NULL AUTO_INCREMENT, " +
        "name VARCHAR(255), " +
        "second_name VARCHAR(255), " +
        "PRIMARY KEY (id)" +
        ")";
    entityManager
        .createNativeQuery(sql)
        .executeUpdate();
  }

  @Override
  public void dropTable() {
    String sql = "DROP TABLE IF EXISTS users";
    entityManager
        .createNativeQuery(sql)
        .executeUpdate();
  }

  @Override
  public void save(User user) {
    entityManager.persist(user);
  }

  @Override
  public void update(User user) {
    boolean isUserInDatabase = entityManager.find(
        User.class,
        user.getId()
    ) != null;
    if (isUserInDatabase) {
      entityManager.merge(user);
    }
  }

  @Override
  public void removeById(long id) {
    User user = entityManager.find(User.class, id);
    if (user != null) {
      entityManager.remove(user);
    }
  }

  @Override
  public List<User> getAll() {
    return new ArrayList<>(
        entityManager.createQuery(
            "from User",
            User.class
        ).getResultList()
    );
  }

  @Override
  public void cleanTable() {
    String hql = "DELETE FROM User";
    entityManager
        .createQuery(hql)
        .executeUpdate();
  }
}
