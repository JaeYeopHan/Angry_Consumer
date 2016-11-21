package com.ac.web;

import com.ac.domain.Article;
import com.ac.domain.ArticleRepository;
import com.ac.domain.User;
import com.ac.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Jbee on 2016. 10. 19..
 */

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("")
    public String listPage(Model model) {
        model.addAttribute("articles", articleRepository.getArticleList());
        return "/article/article_list";
    }

    @GetMapping("/create")
    public String articleForm(HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            //로그인 모달 창 이벤트를 발생시키는게 더 좋지 않을까!
            return "redirect:/";
        }
        return "/article/article_form";
    }

    @PostMapping("/create")
    public String articleCreate(Article article, HttpSession session) {
        User user = HttpSessionUtils.getUserFromSession(session);
        article.setWriterId(user.getId());//객체에 user id 부여
        article.setId(articleRepository.articleInsert(article, user));
        System.out.println(article);
        return "redirect:/articles";
    }

    @GetMapping("/detail/{id}")//detail에는 article의 id 값을 넣자
    public String articleDetail(@PathVariable int id, Model model) {
        model.addAttribute("article", articleRepository.getArticleByArticleId(id));
        System.out.println(articleRepository.getArticleByArticleId(id));
        return "/article/article_detail";
    }
}
