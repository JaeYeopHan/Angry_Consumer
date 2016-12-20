package com.ac.util;

import com.ac.domain.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

import static com.ac.util.PageUtils.countOfArticleInPage;

/**
 * Created by Jbee on 2016. 12. 19..
 */
public class PageUtils {
    public static final int countOfArticleInPage = 10;
    public static int countOfAllArticle;
    public static int countOfPage;
    public static List<Page> pageIndexList;

    public static void pageSetting(ArticleRepository articleRepository) {
        countOfAllArticle = articleRepository.getcountOfAllArticle();
        countOfPage = (countOfAllArticle / countOfArticleInPage) + 1;
        pageIndexList = new ArrayList<>();
        for (int i = 1; i < countOfPage + 1; i++) {
            pageIndexList.add(new Page(i));
        }
    }

    public static int getPageNum(int pageIdx) {
        Page page = pageIndexList.get(pageIdx - 1);
        return page.getArticleStartNum();
    }
}

class Page {
    private int pageNum;

    protected Page(int pageNum) {
        this.pageNum = pageNum;
    }

    protected int getArticleStartNum() {
        return (this.pageNum - 1) * countOfArticleInPage;
    }
}