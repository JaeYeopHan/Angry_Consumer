package com.ac.web;

import com.ac.domain.ArticleRepository;
import com.ac.domain.User;
import com.ac.domain.UserRepository;
import com.ac.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/create")
    public String signup(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            System.out.println("Email is already Exist!");
            return "redirect:/";
        }

        if(userRepository.findUserByName(user.getName()) != null) {
            System.out.println("Name is already Exist!");
            return "redirect:/";
        }

        userRepository.userInsert(user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            System.out.println("Not existed user!");
            return "redirect:/";
        }

        if (!user.matchPassword(password)) {
            System.out.println("Password is wrong!");
            return "redirect:/";
        }

        if(user.equals(HttpSessionUtils.getUserFromSession(session))) {
            System.out.println("Already Login user!");
            return "redirect:/";
        }

        int userId = user.getId();
        userRepository.callStoredProcedure(userId);
        user = userRepository.findUserById(userId);
        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/form")
    public String updateForm(Model model, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            //로그인 모달 창 이벤트를 발생시키는게 더 좋지 않을까!
            return "redirect:/";
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        loginUser.setSumOfAgree(articleRepository.getSumOfAgreeByUserId(loginUser.getId()));
        model.addAttribute("loginUser", loginUser);
        return "/user/user_info";
    }

    @PutMapping("/form")
    public String update(String name, String password, String passwordForConfirm, HttpSession session){
        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        if(password != ""){
            if(password.equals(passwordForConfirm)){
                sessionUser.updateUserInfo(name, password);
                userRepository.userInfoUpdate(sessionUser);
                return "redirect:/users/form";
            }
        }
        sessionUser.updateUserInfo(name);
        userRepository.userInfoUpdate(sessionUser);
        return "redirect:/users/form";
    }
}
