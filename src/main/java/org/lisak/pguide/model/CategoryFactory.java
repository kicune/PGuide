package org.lisak.pguide.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 13.08.13
 * Time: 15:01
 */
public class CategoryFactory {
    static CategoryFactory categoryFactory;
    private List<Category> categoryList;

    public CategoryFactory() {
        categoryFactory = this;
    }

    public static CategoryFactory getInstance() {
        return categoryFactory;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Category getCategory(String categoryId) {
        for(Category c : categoryList) {
            if(c.getId().equals(categoryId))
                return c;
        }
        return null;
    }
}
