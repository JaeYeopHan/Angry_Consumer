package com.ac.web;

import com.ac.domain.Article;
import com.ac.domain.ArticleRepository;
import com.ac.domain.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Jbee on 2016. 10. 17..
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("")
    public String mainPage(Model model) {

        List<Article> articleList = articleRepository.getArticleListCountOfSix();

        for(Article article : articleList) {
            int imageId = article.getIdImage();
            String imagePath = imageRepository.getArticleImagePathById(imageId);
            article.setFilePath(imagePath);
        }

        model.addAttribute("articles", articleList);
        return "index";
    }

}
