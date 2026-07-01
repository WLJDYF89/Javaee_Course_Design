package guat.lxy.service;

import guat.lxy.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryAll();
    Category getById(Integer id);
    void save(Category category);
    void delete(Integer id);
    void clearCategoryCache();
}
