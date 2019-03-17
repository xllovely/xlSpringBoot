package com.xl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//整合mybatis 扫描dao层接口
@MapperScan("com.xl.springboot.dao")
public class XlSpringApplicationMain {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(XlSpringApplicationMain.class,args);
    }
}
