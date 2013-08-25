package org.lisak.pguide.controllers;

import org.apache.commons.io.IOUtils;
import org.lisak.pguide.dao.ContentDao;
import org.lisak.pguide.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 11.08.13
 * Time: 7:37
 */
@Controller
public class MainController {
    @Autowired
    ContentDao contentDao;
    @Autowired
    CategoryFactory categoryFactory;

    @RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    public void showImage(@PathVariable("imageId") String imageId, HttpServletResponse response) throws IOException {
        //FIXME: allow different image types (png?)
        response.setContentType("image/jpeg");
        Image img = (Image)contentDao.get(imageId);
        //FIXME: process errors (wrong ID)
        ByteArrayInputStream bis = new ByteArrayInputStream(img.getData());
        IOUtils.copy(bis, response.getOutputStream());
    }

    @RequestMapping(value="/article/{articleId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable("articleId") String articleId, Model model) {
        Article article = (Article)contentDao.get(articleId);
        model.addAttribute(article);
        return "showArticle";
    }

    @RequestMapping(value="/profile/{profileId}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("profileId") String profileId, Model model) {
        Profile profile = (Profile)contentDao.get(profileId);
        model.addAttribute(profile);
        return "showProfile";
    }

    //returns JSON via ResponseBody annotation
    @RequestMapping(value = "/poi.json")
    public @ResponseBody List<MapMarkersCategory>  getMapJson() {
        List<MapMarkersCategory> mapMarkersCategoryList = new ArrayList<MapMarkersCategory>();

        //cycle categories
        for(Category c : categoryFactory.getCategoryList()) {
            //get all profiles for a category
            MapMarkersCategory mmc = new MapMarkersCategory();
            List<Profile> profileList = contentDao.getProfiles(c.getId());
            for(Profile profile : profileList) {
                mmc.addItem(profile);
            }
            mapMarkersCategoryList.add(mmc);
        }
        return mapMarkersCategoryList;
    }
}