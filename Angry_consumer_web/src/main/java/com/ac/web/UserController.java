package com.ac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jbee on 2016. 10. 19..
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping("/form")
    public String update(){
        return "/user/user_info";
    }
}
