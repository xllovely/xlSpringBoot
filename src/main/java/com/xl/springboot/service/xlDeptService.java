package com.xl.springboot.service;


import java.util.List;
import java.util.Map;
/**
 * 部门管理
 */
public interface xlDeptService {
    /**
     * 部门列表
     * @return
     */
    List<Map> getList();

    /**
     * 添加部门
     * @param map
     * @return
     */
    int addDept(Map map);
}
