package com.xl.springboot.service;

import java.util.List;
import java.util.Map;

/**
 * @className:EmpService
 * @discription:
 * @author:zz
 * @crateTime:2018-11-26 10:43
 */
public interface EmpService {

    /**
     * 雇员列表
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 插叙分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 雇员添加
     * @param map
     * @return
     */
    int  add(Map map);

    /**
     * 雇员更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 雇员删除
     * @param empNo
     * @return
     */
    int delete(Integer empNo);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);
}
