package com.ac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Jbee on 2016. 10. 17..
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

}
