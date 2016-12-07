package com.ac.util;

import com.ac.domain.Article;
import com.ac.domain.ArticleRepository;
import com.ac.domain.User;
import com.ac.domain.UserRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by Jbee on 2016. 12. 7..
 */
public class CheckUserUtils {
    public static Article check(int id, HttpSession session, ArticleRepository articleRepository, UserRepository userRepository) {
        Article article = articleRepository.getArticleByArticleId(id);
        User user = userRepository.findUserById(article.getWriterId());
        if (!user.equals(HttpSessionUtils.getUserFromSession(session))) {
            throw new IllegalStateException("자신의 글만 수정, 삭제할 수 있습니다.");
        }
        return article;
    }
}