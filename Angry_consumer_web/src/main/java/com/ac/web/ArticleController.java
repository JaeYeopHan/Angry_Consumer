package com.ac.web;

import com.ac.domain.Article;
import com.ac.domain.ArticleRepository;
import com.ac.domain.User;
import com.ac.domain.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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
        article.setWriterId(user.getId());
        article.setId(articleRepository.articleInsert(article, user));
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String showArticleDetail(@PathVariable int id, Model model, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            //로그인 모달 창 이벤트를 발생시키는게 더 좋지 않을까!
            return "redirect:/";
        }

        User sessionedUser = HttpSessionUtils.getUserFromSession(session);
        Article article = articleRepository.getArticleByArticleId(id);
        User articleWriter = userRepository.findUserById(article.getWriterId());
        articleWriter.setGrade(userRepository.getUserGrade(articleWriter));
        article.setWriter(articleWriter);
        model.addAttribute("article", article);
        if (sessionedUser.equals(articleWriter)) {
            model.addAttribute("myArticle", article);
        }

        return "/article/article_detail";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id, Model model, HttpSession session) {
        System.out.println("    delete!");
        Article article = articleRepository.getArticleByArticleId(id);
        User user = userRepository.findUserById(article.getWriterId());
        if(!user.equals(HttpSessionUtils.getUserFromSession(session))) {
            return "/articles";
        }
        articleRepository.deleteArticle(id);
        return "/articles";
    }
}
