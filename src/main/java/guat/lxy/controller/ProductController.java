package guat.lxy.controller;

import com.github.pagehelper.PageInfo;
import guat.lxy.entity.Category;
import guat.lxy.entity.Product;
import guat.lxy.service.CategoryService;
import guat.lxy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String productList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              Integer catId, String name,
                              Double minPrice, Double maxPrice,
                              Model model){
        PageInfo<Product> pageInfo = productService.pageProduct(pageNum, pageSize, catId, name, minPrice, maxPrice);
        List<Category> cateList = categoryService.getCategoryAll();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("cateList", cateList);
        model.addAttribute("catId", catId);
        model.addAttribute("name", name);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "product/productList";
    }

    @GetMapping("/edit")
    public String toAdd(Model model) {
        List<Category> cateList = categoryService.getCategoryAll();
        model.addAttribute("cateList", cateList);
        model.addAttribute("product", new Product());
        return "product/productEdit";
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        List<Category> cateList = categoryService.getCategoryAll();
        model.addAttribute("product", product);
        model.addAttribute("cateList", cateList);
        return "product/productEdit";
    }

    @PostMapping("/save")
    public String save(Product product) {
        productService.save(product);
        return "redirect:/success?returnUrl=/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}
