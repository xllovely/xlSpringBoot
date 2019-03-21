package com.xl.springboot.service;

import com.xl.springboot.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @className:EmpServiceImp
 * @discription:
 * @author:zz
 * @crateTime:2018-11-26 10:46
 */
@Service
public class EmpServiceImp implements  EmpService {

    //依赖注入
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Map> getList(Map map) {
        return empDao.getList(map);
    }

    @Override
    public int getPageCount(Map map) {
        return empDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return empDao.add(map);
    }

    @Override
    public int update(Map map) {
        return empDao.update(map);
    }

    @Override
    public int delete(Integer empNo) {
        return empDao.delete(empNo);
    }

    @Override
    public int batchDelete(String ids) {
        boolean isAdd=true;
        if(ids!=null&&!"".equals(ids)){
            String[] idArr = ids.split(",");
            for (String id : idArr) {
               int i= empDao.delete(Integer.valueOf(id));
               if(i<1) isAdd=false;
            }
        }
        if(isAdd) return 1;
        else  return 0;
    }
}
