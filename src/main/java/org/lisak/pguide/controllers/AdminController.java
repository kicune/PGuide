package org.lisak.pguide.controllers;

import org.lisak.pguide.dao.ContentDao;
import org.lisak.pguide.model.Article;
import org.lisak.pguide.model.Image;
import org.lisak.pguide.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 13:59
 */
 @Controller
 @RequestMapping("/admin")
public class AdminController {
    @Autowired
    ContentDao contentDao;

    // ******** Article upload & processing **********
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable("articleId") String articleId, Model model) {
        List<Article> articleList = contentDao.getArticles();
        Collections.sort(articleList, Article.TitleComparator);
        model.addAttribute("articleList", articleList);

        Article article = (Article) contentDao.get(articleId);
        model.addAttribute(article);
        return "articleForm";
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String newArticle(Model model) {
        List<Article> articleList = contentDao.getArticles();
        model.addAttribute("articleList", articleList);

        model.addAttribute(new Article());
        return "articleForm";
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String saveArticle(Article article) {
        contentDao.save(article);
        return "redirect:/admin/article";
    }

    // ******** Image upload & processing **********

    @RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    public String showImage(@PathVariable("imageId") String imageId,
                            @RequestParam(value = "filter", required = false) String filter, Model model) {
        List<Image> imgList;
        if(filter==null) {
            imgList =  contentDao.getImages();
        } else {
            imgList = contentDao.getImages(filter);
            model.addAttribute(filter);
        }
        model.addAttribute("imageList", imgList);

        Image image = (Image) contentDao.get(imageId);
        model.addAttribute(image);
        return "imageForm";
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String newImage(@RequestParam(value = "filter", required = false) String filter, Model model) {
        List<Image> imgList;
        if(filter==null) {
            imgList =  contentDao.getImages();
        } else {
            imgList = contentDao.getImages(filter);
            model.addAttribute(filter);
        }
        model.addAttribute("imageList", imgList);

        model.addAttribute(new Image());
        return "imageForm";
    }

    @RequestMapping(value = "image", method = RequestMethod.POST)
    public String saveImage(Image image, @RequestParam(value = "imageData") MultipartFile data){
        try {
            image.setData(data.getBytes());
            image.setFileType("jpg");
        }   catch (IOException e) {
            return "imageForm";
        }
        contentDao.save(image);

        return "redirect:/admin/image";
    }

    // ******** Profile upload & processing **********
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String newProfile(Model model) {
        List<Profile> profileList;
        profileList =  contentDao.getProfiles();
        model.addAttribute(profileList);

        model.addAttribute(new Profile());
        return "imageForm";
    }
}
