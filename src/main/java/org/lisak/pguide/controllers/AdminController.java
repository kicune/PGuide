package org.lisak.pguide.controllers;

import org.lisak.pguide.dao.ArticleDao;
import org.lisak.pguide.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    ArticleDao articleDao;


    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String listArticles(Model model) {
        List<Article> aList = articleDao.getAll();
        model.addAttribute("articleList", aList);

        model.addAttribute("article", new Article());

        return "listArticles";
    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable("articleId") String articleId, Model model) {
        Article article = articleDao.get(articleId);
        model.addAttribute(article);
        return "articleForm";
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String newArticle(Model model) {
        model.addAttribute(new Article());
        return "articleForm";
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String saveArticle(Model model, Article article) {
        articleDao.save(article);
        return "redirect:/admin/articles";
    }

}
