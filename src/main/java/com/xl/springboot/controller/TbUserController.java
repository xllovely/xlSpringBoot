package com.xl.springboot.controller;

import com.xl.springboot.entity.Message;
import com.xl.springboot.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tbuser")
public class TbUserController {
    @Autowired
    @Qualifier("one")
    private TbUserService tbUserService;
    @RequestMapping("/getTbUserList")
    public Object getTbUserList(){
        return tbUserService.getTbUserList();
    }

    @RequestMapping("/getUserById")
    public Map getUserById(@RequestParam Integer id){
            return  tbUserService.getTbUserById(id);
    }
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
}
