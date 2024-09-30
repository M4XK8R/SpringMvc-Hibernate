package com.maxkor.users;

import com.maxkor.users.config.AppConfig;
import com.maxkor.users.model.User;
import com.maxkor.users.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfig.class
    );
    UserService userService = context.getBean(
        UserService.class
    );

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
















