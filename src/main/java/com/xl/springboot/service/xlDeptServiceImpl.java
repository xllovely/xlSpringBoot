package com.xl.springboot.service;

import com.xl.springboot.dao.xlDeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 部门管理
 */
@Service
public class xlDeptServiceImpl  implements xlDeptService{
    @Autowired
    private xlDeptDao xlDeptDao;

    /**
     * 部门列表
     * @return
     */
    @Override
    public List<Map> getList() {
        return xlDeptDao.getList();
    }

    /**
     * 添加部门
     * @param map
     * @return
     */
    @Override
    public int addDept(Map map) {
        return xlDeptDao.addDept(map);
    }
}
