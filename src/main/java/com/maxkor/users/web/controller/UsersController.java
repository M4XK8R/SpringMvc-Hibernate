package com.maxkor.users.web.controller;

import com.maxkor.users.data.model.User;
import com.maxkor.users.data.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

  private final UserService userService;

  UsersController(UserService userService) {
    this.userService = userService;
  }

    @GetMapping("/list")
  public String getUsers(ModelMap model) {
    model.addAttribute(
        "users",
        userService.getAll()
    );
    return "users";
  }

  @GetMapping("/add")
  public RedirectView addUser(
      @RequestParam(name = "name") String name,
      @RequestParam(name = "second_name") String secondName
  ) {
    userService.upsert(new User(name, secondName));
    return new RedirectView("/springmvc-hibernate/list");
  }

  @GetMapping("/update")
  public RedirectView updateUser(
      @RequestParam(name = "id") int id,
      @RequestParam(name = "name") String name,
      @RequestParam(name = "second_name") String secondName
  ) {
    userService.upsert(new User(id, name, secondName));
    return new RedirectView("/springmvc-hibernate/list");
  }

  @GetMapping("/delete")
  public RedirectView deleteUser(
      @RequestParam(name = "id") int id
  ) {
    userService.removeById(id);
    return new RedirectView("/springmvc-hibernate/list");
  }
}
