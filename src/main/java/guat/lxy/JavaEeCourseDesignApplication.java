package guat.lxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication

@MapperScan("guat.lxy.mapper")
@EnableCaching // 开启Spring声明式缓存

public class JavaEeCourseDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEeCourseDesignApplication.class, args);
    }

}
