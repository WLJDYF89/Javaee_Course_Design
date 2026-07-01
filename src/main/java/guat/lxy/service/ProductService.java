package guat.lxy.service;

import com.github.pagehelper.PageInfo;
import guat.lxy.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProduct();
    PageInfo<Product> pageProduct(Integer pageNum, Integer pageSize,
                                  Integer catId, String name,
                                  Double minPrice, Double maxPrice);
    Product getById(Integer id);
    void save(Product product);
    void delete(Integer id);
}
