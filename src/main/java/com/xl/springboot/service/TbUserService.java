package com.xl.springboot.service;


import java.util.List;
import java.util.Map;

public interface TbUserService {
    /**
     * 用户列表
     * @return
     */
      List<Map> getTbUserList();
    /**
     * 添加用户
     * @param map
     */
    boolean addTbUser(Map map);

    /**
     * 查询用户
     * @param id
     * @return
     */
    Map getTbUserById(Integer id);
    /**
     * 修改用户
     * @param map
     */
    boolean updateTbUser(Map map);

    /**
     * 删除用户
     * @param id
     */
    boolean deleteTbUser(Integer id);
}
