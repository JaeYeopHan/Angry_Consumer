package com.ac.util;

import com.ac.domain.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jbee on 2016. 12. 19..
 */
public class PageUtils {
    public static final int countOfArticleInPage = 10;
    public static int countOfArticle;
    public static int countOfPage;
    public static List<Page> pageList;

    public static void pageSetting(ArticleRepository articleRepository) {
        countOfArticle = articleRepository.getSumOfArticle();
        countOfPage = (countOfArticle / countOfArticleInPage) + 1;
        pageList = new ArrayList<>();
        for (int i = 0; i < countOfPage; i++) {
            pageList.add(new Page(i));
        }
    }

    public static int getCountOfPage(int id) {
        Page page = pageList.get(id - 1);
        return page.getLimitNum();
    }
}

class Page {
    private int pageNum;
    private int limitNum;

    protected Page(int pageNum) {
        this.pageNum = pageNum + 1;
        this.limitNum = pageNum * 10;
    }

    protected int getLimitNum() {
        return limitNum;
    }
}