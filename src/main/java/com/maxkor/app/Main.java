package com.maxkor.app;

import com.maxkor.feature.users.data.entity.User;
import com.maxkor.feature.users.data.service.UserService;
import com.maxkor.feature.users.data.service.UserServiceImpl;


public class Main {

  public static void main(String[] args) {
    UserService userService = new UserServiceImpl();

    for (int i = 1; i <= 5; i++) {
      userService.upsert(
          new User(
              "name_" + i,
              "secondName_" + i
          )
      );
    }

    userService.getAll()
        .forEach(System.out::println);

    userService.removeById(1);
    userService.removeById(0);

    System.out.println();

    for (int i = 1; i <= 10; i++) {
      userService.upsert(
          new User(
              i,
              "name_" + i * 2,
              "secondName_" + i * 2
          )
      );
    }

    userService.getAll()
        .forEach(System.out::println);
  }
}
















