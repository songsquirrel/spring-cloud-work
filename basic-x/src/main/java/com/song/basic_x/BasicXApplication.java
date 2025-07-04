package com.song.basic_x;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.song.basicx.test.mapper"})
public class BasicXApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicXApplication.class, args);
    }

}
