package guat.lxy.mapper;

import guat.lxy.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM category")
    List<Category> selectAll();

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(@Param("id") Integer id);

    @Insert("INSERT INTO category(name, descp) VALUES(#{name}, #{descp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Category category);

    @Update("UPDATE category SET name = #{name}, descp = #{descp} WHERE id = #{id}")
    int update(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int delete(@Param("id") Integer id);
}
