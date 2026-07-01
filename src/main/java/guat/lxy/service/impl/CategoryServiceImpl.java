package guat.lxy.service.impl;

import guat.lxy.entity.Category;
import guat.lxy.mapper.CategoryMapper;
import guat.lxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORY_KEY = "category:all_list";
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryAll() {
        List<Category> list;
        Object cacheObj = redisTemplate.opsForValue().get(CATEGORY_KEY);
        if(cacheObj != null){
            list = (List<Category>) cacheObj;
            System.out.println("=====命中Redis分类缓存=====");
        } else {
            System.out.println("=====未命中Redis分类缓存，执行SQL查询=====");
            list = categoryMapper.selectAll();
            redisTemplate.opsForValue().set(CATEGORY_KEY, list, 30, TimeUnit.MINUTES);
        }
        System.out.println("<==    Columns: id, name, descp");
        list.forEach(c -> System.out.println("<==        Row: " + c.getId() + ", " + c.getName() + ", " + c.getDescp()));
        System.out.println("<==      Total: " + list.size());
        return list;
    }


    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    @Transactional
    public void save(Category category) {
        if (category.getId() == null) {
            categoryMapper.insert(category);
        } else {
            categoryMapper.update(category);
        }
        clearCategoryCache();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoryMapper.delete(id);
        clearCategoryCache();
    }

    @Override
    public void clearCategoryCache() {
        redisTemplate.delete(CATEGORY_KEY);
    }
}
