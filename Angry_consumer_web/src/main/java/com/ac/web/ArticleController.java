package com.ac.web;

import com.ac.domain.*;
import com.ac.util.FileUploadUtils;
import com.ac.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("")
    public String listPage(Model model) {
        List<Article> articleList = articleRepository.getArticleList();

        for(Article article : articleList) {
            int imageId = article.getIdImage();
            String fileName = imageRepository.getArticleImagePathById(imageId);
            article.setFileName(fileName);
        }

        model.addAttribute("articles", articleList);
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

        MultipartFile uploadFile = article.getUploadFile();
        if (uploadFile != null) {
            String fileName = FileUploadUtils.fileUpload(uploadFile);
            int idImage = imageRepository.insertArticleImage(fileName);
            article.setIdImage(idImage);
        }

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

        int imageId = article.getIdImage();
        String fileName = imageRepository.getArticleImagePathById(imageId);
        article.setFileName(fileName);

        model.addAttribute("article", article);
        if (sessionedUser.equals(articleWriter)) {
            model.addAttribute("myArticle", article);
        }

        return "/article/article_detail";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id, Model model, HttpSession session) {
        Article article = articleRepository.getArticleByArticleId(id);
        User user = userRepository.findUserById(article.getWriterId());
        if (!user.equals(HttpSessionUtils.getUserFromSession(session))) {
            throw new IllegalStateException("자신의 글만 삭제할 수 있습니다.");
        }
        articleRepository.deleteArticle(id);
        return "/articles";
    }

    @GetMapping("/{id}/form")
    public String articleUpdateForm(@PathVariable int id, Model model, HttpSession session) {
        Article article = articleRepository.getArticleByArticleId(id);
        User user = userRepository.findUserById(article.getWriterId());
        if (!user.equals(HttpSessionUtils.getUserFromSession(session))) {
            throw new IllegalStateException("자신의 글만 수정할 수 있습니다.");
        }
        model.addAttribute("article", article);
        return "article/article_update_form";
    }

    //추후 PutMapping으로 수정!
    @PostMapping("/{id}/update")
    public String updateArticle(@PathVariable int id, Article updatedArticle, HttpSession session) {
        Article article = articleRepository.getArticleByArticleId(id);
        User user = userRepository.findUserById(article.getWriterId());
        if (!user.equals(HttpSessionUtils.getUserFromSession(session))) {
            throw new IllegalStateException("자신의 글만 수정할 수 있습니다.");
        }
        articleRepository.updateArticle(updatedArticle, article.getId());
        return "redirect:/articles/" + id;
    }
}