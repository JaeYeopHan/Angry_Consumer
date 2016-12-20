package com.ac.web;

import com.ac.domain.*;
import com.ac.util.CheckUserUtils;
import com.ac.util.FileUploadUtils;
import com.ac.util.HttpSessionUtils;
import com.ac.util.PageUtils;
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

    private static final String ALL_RANGE_SEARCH = "ALL";

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/classify/{option}")
    public String articleListByOption(@PathVariable String option, Model model) {
        List<Article> articleList = articleRepository.getArticleListByOption(option);
        model.addAttribute("articles", articleList);
        return "/article/article_list";
    }

    @GetMapping("/page/{pageIdx}")
    public String paging(@PathVariable int pageIdx, Model model) {
        PageUtils.pageSetting(articleRepository);
        int pageNum = PageUtils.getPageNum(pageIdx);
        List<Article> articleList = articleRepository.getArticleListByPage(pageNum);
        model.addAttribute("articles", articleList);
        model.addAttribute("pages", PageUtils.pageIndexList);
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
        article.setId(articleRepository.insertArticle(article, user));

        return "redirect:/articles/page/1";
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
        article.setWriter(articleWriter);

        model.addAttribute("article", article);
        if (sessionedUser.equals(articleWriter)) {
            model.addAttribute("myArticle", article);
        }

        List<Comment> commentList = commentRepository.getListOfComments(id);
        model.addAttribute("comment", commentList);
        articleRepository.updateHitOfArticle(id);

        return "/article/article_detail";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id, HttpSession session) {
        Article article = CheckUserUtils.check(id, session, articleRepository, userRepository);
        articleRepository.deleteArticle(id);
        return "/articles/page/1";
    }

    @GetMapping("/{id}/form")
    public String articleUpdateForm(@PathVariable int id, Model model, HttpSession session) {
        Article article = CheckUserUtils.check(id, session, articleRepository, userRepository);
        model.addAttribute("article", article);
        return "article/article_update_form";
    }

    @PostMapping("/{id}/update")
    public String updateArticle(@PathVariable int id, Article updatedArticle, HttpSession session) {
        Article article = CheckUserUtils.check(id, session, articleRepository, userRepository);
        articleRepository.updateArticle(updatedArticle, article.getId());
        return "redirect:/articles/" + id;
    }

    @GetMapping("/search")
    public String searchArticle(@RequestParam("query") String query, @RequestParam("searchRange") String searchRange, Model model) {
        List<Article> articleList;
        if (searchRange.equals(ALL_RANGE_SEARCH)) {
            articleList = articleRepository.getArticleListByQuery(query);
        } else {
            articleList = articleRepository.getArticleListByQueryOfRange(query, searchRange);
        }

        model.addAttribute("articles", articleList);
        return "/article/article_list";
    }

    @PutMapping("/{id}/agree")
    @ResponseBody
    public String updateAgreeOfArticle(@PathVariable int id) {
        articleRepository.updateAgreeOfArticle(id);
        return "이 글에 공감하셨습니다.";
    }
}