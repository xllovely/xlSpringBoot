package com.xl.springboot.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 */
@Repository
public interface xlDeptDao {
    /**
     * 部门列表
     * @return
     */
    @Select("select * from tb_dept")
    List<Map> getList();

    /**
     * 添加部门
     * @param map
     * @return
     */
    @Insert("insert into tb_dept (dname,loc,pic) value(#{dname},#{loc},#{pic})")
    int addDept(Map map);
}
