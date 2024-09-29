package com.maxkor.feature.users.data.dao;

import com.maxkor.feature.users.data.entity.User;
import com.maxkor.feature.users.data.util.HibernateJpaUtil;
import com.maxkor.core.base.util.LoggingUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

public class UserDaoHibernateJpaImpl implements UserDao {

  public UserDaoHibernateJpaImpl() {

  }

  @Override
  @Transactional
  public void createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS users (" +
        "id BIGINT NOT NULL AUTO_INCREMENT, " +
        "name VARCHAR(255), " +
        "second_name VARCHAR(255), " +
        "PRIMARY KEY (id)" +
        ")";

    run(entityManager -> entityManager
        .createNativeQuery(sql)
        .executeUpdate());
  }

  @Override
  @Transactional
  public void dropTable() {
    String sql = "DROP TABLE IF EXISTS users";
    run(entityManager -> entityManager
        .createNativeQuery(sql)
        .executeUpdate());
  }

  @Override
  @Transactional
  public void save(User user) {
    run(entityManager ->
        entityManager.persist(user));
  }

  @Override
  @Transactional
  public void update(User user) {
    run(entityManager -> {
      boolean isUserInDatabase = entityManager.find(
          User.class,
          user.getId()
      ) != null;
      if (isUserInDatabase) {
        entityManager.merge(user);
      }
    });
  }

  @Override
  @Transactional
  public void removeById(long id) {
    run(entityManager -> {
      User user = entityManager.find(
          User.class,
          id
      );
      if (user != null) {
        entityManager.remove(user);
      }
    });
  }

  @Override
  @Transactional
  public List<User> getAll() {
    List<User> users = new ArrayList<>();

    run(entityManager -> users.addAll(
        entityManager.createQuery(
            "from User",
            User.class
        ).getResultList()
    ));

    return users;
  }

  @Override
  @Transactional
  public void cleanTable() {
    String hql = "DELETE FROM User";

    run(entityManager -> entityManager
        .createQuery(hql)
        .executeUpdate());
  }

  /*
Private sector
 */
  private void run(Consumer<EntityManager> entityManagerConsumer) {
    if (entityManagerConsumer == null) {
      return;
    }
    EntityManager entityManager = null;
    EntityTransaction transaction = null;

    try {
      entityManager = HibernateJpaUtil.getEntityManager();
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManagerConsumer.accept(entityManager);
      transaction.commit();
    } catch (Exception e) {
      LoggingUtil.printExceptionInfo(
          "UserDaoHibernateJpaImpl",
          "run",
          e
      );
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      throw (e);
    } finally {
      if (entityManager != null && entityManager.isOpen()) {
        try {
          entityManager.close();
        } catch (Exception e) {
          LoggingUtil.printExceptionInfo(
              "UserDaoHibernateJpaImpl",
              "run",
              e
          );
          throw (e);
        }
      }
    }
  }
}
