package guat.lxy.mapper;

import guat.lxy.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 商品左连分类表，查询所有商品附带分类名
    @Select("SELECT p.*, c.name AS category_name FROM product p LEFT JOIN category c ON p.cat_id = c.id")
    List<Product> findAllProductWithCategory();

    // 根据ID查商品（用于缓存注解）
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product selectById(@Param("id") Integer id);

    // 多条件动态查询（对应 ProductMapper.xml 中的 SQL）
    List<Product> selectProductByCondition(@Param("catId") Integer catId,
                                           @Param("name") String name,
                                           @Param("minPrice") Double minPrice,
                                           @Param("maxPrice") Double maxPrice);

    // 新增商品
    @Insert("INSERT INTO product(name, photo_url, price, descp, release_date, cat_id) " +
            "VALUES(#{name}, #{photoUrl}, #{price}, #{descp}, #{releaseDate}, #{catId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    // 更新商品（动态SQL，在XML中定义）
    int update(Product product);

    // 删除商品
    @Delete("DELETE FROM product WHERE id = #{id}")
    int delete(@Param("id") Integer id);
}
