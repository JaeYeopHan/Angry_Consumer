package com.ac.web;

import com.ac.domain.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jbee on 2016. 10. 17..
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("")
    public String mainPage(Model model) {
        model.addAttribute("articles", articleRepository.getArticleListCountOfSix());
        return "index";
    }

}
