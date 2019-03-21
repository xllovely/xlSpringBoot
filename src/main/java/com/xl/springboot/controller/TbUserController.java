package com.xl.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.springboot.entity.Message;
import com.xl.springboot.service.TbUserService;
import com.xl.springboot.util.PageUtil;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tbuser")
public class TbUserController {
    @Autowired
    @Qualifier("one")
    private TbUserService tbUserService;
    @ResponseBody
    @RequestMapping("/getTbUserList")
    public Object getTbUserList(){
        return tbUserService.getTbUserList();
    }
    @ResponseBody
    @RequestMapping("/getUserById")
    public Map getUserById(@RequestParam Integer id){
            return  tbUserService.getTbUserById(id);
    }
    @ResponseBody
    @RequestMapping("/addOrUpdateTbUser")
    public Message addOrUpdateTbUser(@RequestParam Map map){
        System.out.println(111111);
        System.out.println(111);
        Message message=new Message();
        if(map!=null&&!map.get("id").equals("")){
            if(tbUserService.updateTbUser(map)){
                message.setMessage("修改成功");
                message.setState(true);
            }else{
                message.setMessage("修改失败");
                message.setState(false);
            }
        }else{
            if(tbUserService.addTbUser(map)){
                message.setMessage("添加成功");
                message.setState(true);
            }else{
                message.setMessage("添加失败");
                message.setState(false);
            }
        }

        return message;
    }
    @ResponseBody
    @RequestMapping("deleteTbUser")
    public Message deleteTbUser(@RequestParam Integer id){
        Message message=new Message();

            if(tbUserService.deleteTbUser(id)){
                message.setMessage("删除成功");
                message.setState(true);
            }else{
                message.setMessage("删除失败");
                message.setState(false);
            }


        return message;
    }
    @RequestMapping("/listPage")
    public String listPage(@RequestParam Map map, HttpServletRequest request, Model model){
        map.put("pageNo",map.get("pageNo")==null?1:map.get("pageNo"));
        map.put("pageSize",2);
        //获取分页 数据
        List<Map> userPage=tbUserService.getUserList(map);
        model.addAttribute("userPage",userPage);
        //获取总条数
        int total = tbUserService.getUserPageCount(map);
        //使用分页工具类
        String pageString = new PageUtil(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""),total, request).getPageString();
        System.out.println(pageString);
        model.addAttribute("pageString",pageString);
        model.addAttribute("map",map);
        return "user/listpage";
    }

    /**
     * 分页查询方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestParam Map map){
        //设置第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        PageInfo pageInfo=new PageInfo<Map>(tbUserService.getUserList(null));
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(pageInfo);
        mappingJacksonValue.setJsonpFunction(map.get("xl")+"");
        return mappingJacksonValue;
    }
    /**
     * 分页查询方法(讲解JsonP)
     * @param map
     * @return
     */
    @RequestMapping("/pageJsonP")//限制该方法只能post
    //@RequestMapping(value="/pageJsonP",method = RequestMethod.POST)//json只能get
    public void pageJsonP(@RequestParam Map map, HttpServletResponse response) throws IOException {
        //设置第几页和每页显示数量
/*
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
*/
        PageInfo pageInfo=new PageInfo<Map>(tbUserService.getUserList(null));
        //设置返回类型编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //把JSON对象转为字符串
        String jsonString = JSONArray.toJSONString(pageInfo);
        /*//获取前台的jsonp参数名xl
        String callback=map.get("xl")+"";
        //使用接收到callbak包裹字符串
        callback=callback+"("+jsonString+")";*/
        /*response.getWriter().print(callback);*/
        response.getWriter().print(jsonString);
    }
}
