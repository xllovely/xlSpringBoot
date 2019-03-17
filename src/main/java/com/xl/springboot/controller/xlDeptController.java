package com.xl.springboot.controller;

import com.xl.springboot.service.xlDeptService;
import com.xl.springboot.util.xlFtpConfig;
import com.xl.springboot.util.xlFtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 部门管理
 */
    @Controller
@RequestMapping("/dept")
public class xlDeptController {
    @Autowired
    private xlDeptService xlDeptService;
    @Autowired
    private xlFtpUtil xlFtpUtil;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private xlFtpConfig xlFtpConfig;
    @RequestMapping("/add")
    public String add(@RequestParam Map map, @RequestParam MultipartFile pic){
        String fileName=xlFtpUtil.upLoad(pic);
        map.put("pic",fileName);
        xlDeptService.addDept(map);
        return "redirect:/html/dept/list.html";
    }
    @ResponseBody
    @RequestMapping("/list")
    public Object list(){
         return xlDeptService.getList();
    }
    @RequestMapping("/showPic")
    public ResponseEntity showPic(String fileName){
      return ResponseEntity.ok(resourceLoader.getResource("ftp://"+xlFtpConfig.getFtpUserName()+":"
              +xlFtpConfig.getFtpPassWord()+"@"+xlFtpConfig.getRemoteIp()+xlFtpConfig.getRemotePath()+fileName));
    }
    @RequestMapping("/downLoadFile")
    public void downLoadFile(String fileName, HttpServletResponse response){
        xlFtpUtil.downLoad(fileName,response);
    }
}
