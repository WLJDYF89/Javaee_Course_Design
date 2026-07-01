package guat.lxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guat.lxy.entity.Product;
import guat.lxy.mapper.ProductMapper;
import guat.lxy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> listAllProduct() {
        return productMapper.findAllProductWithCategory();
    }

    @Override
    public PageInfo<Product> pageProduct(Integer pageNum, Integer pageSize, Integer catId, String name, Double minPrice, Double maxPrice) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.selectProductByCondition(catId, name, minPrice, maxPrice);
        return new PageInfo<>(list);
    }

    @Override
    @Cacheable(value = "product_single", key = "#id")
    public Product getById(Integer id) {
        return productMapper.selectById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "product_single", key = "#product.id")
    public void save(Product product) {
        if (product.getId() == null) {
            productMapper.insert(product);
        } else {
            productMapper.update(product);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "product_single", key = "#id")
    public void delete(Integer id) {
        productMapper.delete(id);
    }
}
