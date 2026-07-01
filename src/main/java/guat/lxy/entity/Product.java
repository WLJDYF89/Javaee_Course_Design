package guat.lxy.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Product {
    private Integer id;
    private String name;
    private String photoUrl;
    private Double price;
    private String descp;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private Integer catId;
    // 联查额外字段：分类名称
    private String categoryName;
}
