package com.ac.web;

import com.ac.domain.User;
import com.ac.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Jbee on 2016. 10. 19..
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public String signup(User user) {
        if (userRepository.existEmail(user.getEmail()) != null) {
            System.out.println("Email is already Exist!");
            return "redirect:/";
        }

        if(userRepository.existName(user.getName()) != null) {
            System.out.println("Name is already Exist!");
            return "redirect:/";
        }

        userRepository.insert(user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        User user = userRepository.existEmail(email);

        if (user == null) {
            System.out.println("Not existed user!");
            return "redirect:/";
        }

        if (!user.matchPassword(password)) {
            System.out.println("Password is wrong!");
            return "redirect:/";
        }

        session.setAttribute("sessionUser", user);
        System.out.println("Login Complete!!" + session.getAttribute("sessionUser"));

        return "redirect:/";
    }

    @GetMapping("/form")
    public String update() {
        return "/user/user_info";
    }
}
