package com.xl.springboot.controller;

import com.xl.springboot.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @className:EmpController
 * @discription:
 * @author:zz
 * @crateTime:2018-11-26 11:07
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 返回列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(@RequestBody Map map){
        System.out.println(map+"......abc.....");
        System.out.println(map.get("start")+","+map.get("end"));
        Map rmap=new HashMap();
        rmap.put("data",empService.getList(map));
        rmap.put("total",empService.getPageCount(map));
        System.out.println(rmap);
        return rmap;
    }

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
         return "user/list";
    }

    /**
     * 删除
     * @param empNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/del/{empNo}")
    public Object del(@PathVariable Integer empNo){
       return empService.delete(empNo);
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDel/{ids}")
    public Object batchDel(@PathVariable String ids){
        return empService.batchDelete(ids);
    }
    /**
     * 添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
       // System.out.println(map+"...aaa....");
        //return 1;
        System.out.println(map);
        return empService.add(map);
    }
    /**
     * 添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return empService.update(map);
    }


}
