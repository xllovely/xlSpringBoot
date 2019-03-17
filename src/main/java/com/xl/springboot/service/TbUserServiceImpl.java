package com.xl.springboot.service;

import com.xl.springboot.dao.TbUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("one")
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    /**
     * 用户列表
     * @return
     */
    @Override
    public List<Map> getTbUserList() {
        return tbUserDao.getTbUserList();
    }

    /**
     * 添加用户
     * @param map
     */
    @Override
    public boolean addTbUser(Map map) {
        try{
            tbUserDao.addTbUser(map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public Map getTbUserById(Integer id) {
        return tbUserDao.getTbUserById(id);
    }

    /**
     * 修改用户
     * @param map
     */
    @Override
    public boolean updateTbUser(Map map) {
        try{
            tbUserDao.updateTbUser(map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public boolean deleteTbUser(Integer id) {
        try{
            tbUserDao.deleteTbUser(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
