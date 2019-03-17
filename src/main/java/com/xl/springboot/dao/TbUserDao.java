package com.xl.springboot.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Repository
public interface TbUserDao {
    /**
     * 用户列表
     * @return
     */
    @Select("select * from tb_user")
    List<Map> getTbUserList();

    /**
     * 添加用户
     * @param map
     */
    @Insert("insert into tb_user(name,age,email) values(#{name},#{age},#{email})")
    void addTbUser(Map map);

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Select("select * from tb_user where id=#{id}")
    Map getTbUserById(Integer id);

    /**
     * 修改用户
     * @param map
     */
    @Update("update tb_user set name=#{name},age=#{age},email=#{email} where id=#{id}")
    void updateTbUser(Map map);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from tb_user where id=#{id}")
    void deleteTbUser(Integer id);
}
