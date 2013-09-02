package org.lisak.pguide.controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.lisak.pguide.dao.ContentDao;
import org.lisak.pguide.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
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
    private ContentDao contentDao;
    private CategoryFactory categoryFactory;

    public CategoryFactory getCategoryFactory() {
        return categoryFactory;
    }

    public void setCategoryFactory(CategoryFactory categoryFactory) {
        this.categoryFactory = categoryFactory;
    }

    public ContentDao getContentDao() {
        return contentDao;
    }

    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

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
    public String newArticle(HttpServletRequest req, Model model) {

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

    @RequestMapping(value = "/emptyreport", method = RequestMethod.GET)
    public String emptyValueReport(Model model) {
        Formatter fm = new Formatter();
        List<String> resultList = new ArrayList<String>();
        List<Article> articleList = contentDao.getArticles();
        for(Article a:articleList) {
            if(a.getTitle() == null || a.getTitle().equals("")) {
                resultList.add(String.format("Article id %s: empty title", a.getId()).toString());
            }
            if(a.getText() == null || a.getText().equals("")) {
                resultList.add(String.format("Article id %s: empty text", a.getId()));
            }
        }

        for(Profile p: contentDao.getProfiles()) {
            if(p.getName() == null || p.getName().equals("")) {
                resultList.add(String.format("Profile id %s: empty name", p.getId()));
            }
            if(p.getUrl() == null || p.getUrl().equals("")) {
                resultList.add(String.format("Profile id %s: empty url", p.getId()));
            }
            if(p.getGpsCoords() == null || p.getGpsCoords().replaceAll("[0. ]","").equals("")) {
                resultList.add(String.format("Profile id %s: empty GPS coords", p.getId()));
            }
            if(p.getAddress() == null || p.getAddress().equals("")) {
                resultList.add(String.format("Profile id %s: empty address", p.getId()));
            }
            if(p.getOpeningHours() == null || p.getOpeningHours().size() == 0 ||
                    p.getOpeningHours().get(0) == null || p.getOpeningHours().get(0).getMorning().equals("")) {
                resultList.add(String.format("Profile id %s: empty opening hours", p.getId()));
            }
            if(p.getPrices() == null || p.getPrices().equals("")) {
                resultList.add(String.format("Profile id %s: empty prices", p.getId()));
            }
            if(p.getProfileImg() == null || p.getProfileImg().equals("")) {
                resultList.add(String.format("Profile id %s: empty profile image", p.getId()));
            }
            if(p.getStaticMapImg() == null || p.getStaticMapImg().equals("")) {
                resultList.add(String.format("Profile id %s: empty static map image", p.getId()));
            }
            if(p.getProfileImg() == null || p.getProfileImg().equals("")) {
                resultList.add(String.format("Profile id %s: empty profile image", p.getId()));
            }
            if(p.getGallery() == null || p.getGallery().size() == 0) {
                resultList.add(String.format("Profile id %s: empty image gallery", p.getId()));
            }
            if(p.getText() == null || p.getText().equals("")) {
                resultList.add(String.format("Profile id %s: empty text", p.getId()));
            }
        }

        model.addAttribute("emptyValueList", resultList);
        return "admin/emptyValueReport";
    }
}
