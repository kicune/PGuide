package org.lisak.pguide.controllers;

import org.apache.commons.io.IOUtils;
import org.lisak.pguide.dao.ContentDao;
import org.lisak.pguide.model.Article;
import org.lisak.pguide.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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
}
