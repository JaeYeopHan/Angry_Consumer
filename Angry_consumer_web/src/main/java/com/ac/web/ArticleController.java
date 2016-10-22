package com.ac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jbee on 2016. 10. 19..
 */

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("")
    public String listPage() {
        return "/article/article_list";
    }

    @GetMapping("/create")
    public String articleForm(){
        return "/article/article_form";
    }

    @PostMapping("/create")
    public String articleCreate(){
        return "redirect:/articles/list";
    }

    @GetMapping("/detail")//detail에는 article의 id 값을 넣자
    public String articleDetail(){
        return "/article/article_detail";
    }
}
