package ru.spo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.spo.domain.Product;
import ru.spo.domain.User;
import ru.spo.service.ProductServise;
import ru.spo.service.UserServise;

import java.util.List;
import java.util.stream.IntStream;


@Controller

public class UserController {
    @Autowired
    private UserServise userServise;
    private List<User> userList;

    @GetMapping("/users")
    public String listUsers(Model model,
//                          @RequestParam(value = "size", required = false, defaultValue = "0") int size,
                               @RequestParam(value = "page", required = false, defaultValue = "0") int page)
    { Page<User> userPage = userServise.findPaginated(page, 5);
    model.addAttribute("userPage", userPage);
    model.addAttribute("numbers", IntStream.range(0,userPage.getTotalPages()).toArray());
    return "users";
    }

   @RequestMapping("/editUser/{id}")
   public ModelAndView editUser(@PathVariable long id) {
       ModelAndView model = new ModelAndView("edit_user");
       User user = userServise.getById(id);
       model.addObject("user", user);
       return model;
   }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveProduct(@ModelAttribute("user") User user) {
        userServise.save(user);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServise.delete(id);
        return "redirect:/users";
    }




}
