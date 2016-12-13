package com.ac.util;

import com.ac.domain.Article;
import com.ac.domain.ImageRepository;

import java.util.List;

/**
 * Created by Jbee on 2016. 12. 7..
 */
public class ImageSettingUtils {
    public static void settingImageToArticle(List<Article> articleList, ImageRepository imageRepository) {
        for(Article article : articleList) {
            int imageId = article.getIdImage();
            String fileName = imageRepository.getArticleImagePathById(imageId);
            article.setFileName(fileName);
        }
    }
}
