package com.xl.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@Controller
//默认返回Json
@RestController
@RequestMapping("/Hello")
public class xlHelloController {
    /**
     * 示例方法
     * @return
     */
    @RequestMapping("/xlDemo")
      public Object xlDemo(){
          return "Hello Springboot!! 太上老君保佑，永无BUG!!!!";
      }
}
