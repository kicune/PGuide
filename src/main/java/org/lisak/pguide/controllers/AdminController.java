package org.lisak.pguide.controllers;

import org.lisak.pguide.dao.ContentDao;
import org.lisak.pguide.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    CategoryFactory categoryFactory;

    // ******** Article upload & processing **********
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable("articleId") String articleId,
                              @RequestParam(value = "delete", required = false) String delete,
                              Model model) {
        List<Article> articleList = contentDao.getArticles();
        Collections.sort(articleList, Article.TitleComparator);
        model.addAttribute("articleList", articleList);

        Article article;
        if(delete == null) {
            article = (Article) contentDao.get(articleId);
        } else {
            contentDao.delete(articleId);
            article = new Article();
            model.addAttribute(article);
            return "redirect:/admin/article";
        }
        model.addAttribute(article);
        return "admin/articleForm";
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String newArticle(Model model) {
        List<Article> articleList = contentDao.getArticles();
        model.addAttribute("articleList", articleList);

        model.addAttribute(new Article());
        return "admin/articleForm";
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String saveArticle(Article article) {
        contentDao.save(article);
        return "redirect:/admin/article/" + article.getId();
    }

    // ******** Image upload & processing **********

    @RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    public String showImage(@PathVariable("imageId") String imageId,
                            @RequestParam(value = "filter", required = false) String filter,
                            @RequestParam(value = "delete", required = false) String delete,
                            Model model) {
        List<Image> imgList;
        if(filter==null) {
            imgList =  contentDao.getImages();
        } else {
            imgList = contentDao.getImages(filter);
            model.addAttribute(filter);
        }
        model.addAttribute("imageList", imgList);

        Image image;
        if(delete == null) {
            image = (Image) contentDao.get(imageId);
            model.addAttribute(image);
            return "admin/imageForm";
        } else {
            contentDao.delete(imageId);
            return "redirect:/admin/image";
        }
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
        return "admin/imageForm";
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String saveImage(Image image, @RequestParam(value = "imageData") MultipartFile data){
        try {
            if(data.getBytes().length>0) {
                image.setData(data.getBytes());
            } else {
                //fetch original image from DB (otherwise NULL will be saved as image data):
                Image _img = (Image)contentDao.get(image.getId());
                image.setData(_img.getData());
            }

            image.setFileType("jpg");
        }   catch (IOException e) {
            return "admin/imageForm";
        }
        contentDao.save(image);

        return "redirect:/admin/image/" + image.getId();
    }

    // ******** Profile upload & processing **********
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String newProfile(Model model) {
        List<Profile> profileList;
        profileList =  contentDao.getProfiles();
        model.addAttribute(profileList);

        List<Image> imageList = contentDao.getImages();
        model.addAttribute("strImgList", imageList.toString());

        List<Category> categoryList = categoryFactory.getCategoryList();
        model.addAttribute(categoryList);

        model.addAttribute("profile", new Profile());
        return "admin/profileForm";
    }

    @RequestMapping(value = "/profile/{profileId}", method = RequestMethod.GET)
    public String showImage(@PathVariable("profileId") String profileId,
                            @RequestParam(value = "delete", required = false) String delete,
                            Model model) {
        List<Profile> profileList;
        profileList =  contentDao.getProfiles();
        model.addAttribute(profileList);

        List<Image> imageList = contentDao.getImages();
        model.addAttribute("strImgList", imageList.toString());


        List<Category> categoryList = categoryFactory.getCategoryList();
        model.addAttribute(categoryList);

        Profile profile;
        if(delete == null) {
            profile = (Profile) contentDao.get(profileId);
            model.addAttribute(profile);
            return "admin/profileForm";
        } else {
            contentDao.delete(profileId);
            return "redirect:/admin/profile";
        }

    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@ModelAttribute("profile") Profile profile){
        contentDao.save(profile);

        return "redirect:/admin/profile/" + profile.getId();
    }
}
