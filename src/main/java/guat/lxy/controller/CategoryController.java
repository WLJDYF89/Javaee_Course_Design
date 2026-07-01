package guat.lxy.controller;

import guat.lxy.entity.Category;
import guat.lxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String categoryList(Model model){
        List<Category> list = categoryService.getCategoryAll();
        model.addAttribute("categoryList", list);
        return "category/categoryList";
    }

    @GetMapping("/edit")
    public String toAdd(Model model) {
        model.addAttribute("category", new Category());
        return "category/categoryEdit";
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "category/categoryEdit";
    }

    @PostMapping("/save")
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:/success?returnUrl=/category/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/category/list";
    }
}