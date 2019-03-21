package com.xl.springboot.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @className:EmpDao
 * @discription:
 * @author:zz
 * @crateTime:2018-11-26 10:41
 */
@Repository
//添加redis缓存注解
@CacheNamespace(implementation = RedisCache.class)
public interface EmpDao {

    /**
     * 雇员列表
     * @return
     * 重点注意：注解动态sql要加"{}",要加script  大于小于号要&lt;
     */
    @Select({"<script>" +
            "select * from (select empno rn,t.* from tb_emp  t where  empno &lt; #{end} "
            +"<if test=\"id!=null and id!=''\"> and empno=#{id} </if>"
            +"<if test=\"name!=null and name!=''\"> and ename like '%${name}%' </if>"
            +"<if test=\"deptNo!=null and deptNo!=0\"> and deptno=#{deptNo} </if>"
            +")  a where a.rn &gt; #{start} " +
            "</script>"})
    List<Map> getList(Map map);

    /**
     * 插叙分页总数量
     * @param map
     * @return
     */
    @Select({"<script>" +"select count(*) from tb_emp <where>"
            +"<if test=\"id!=null and id!=''\"> and empno=#{id} </if>"
            +"<if test=\"name!=null and name!=''\"> and ename like '%${name}%' </if>"
            +"<if test=\"deptNo!=null and deptNo!=0\"> and deptno=#{deptNo} </if>"+
            "</where></script>"})
    int getPageCount(Map map);
    /**
     * 雇员添加
     * @param map
     * @return
     */
   // @Insert("insert into tb_emp values(seq_tb_emp_id.nextval,#{ENAME},#{JOB},7839,to_date(#{HIREDATE},'yyyy-mm-dd'),#{SAL},0,10)")
    @Insert("insert into tb_emp (ename,job,Nsn,hiredate,sal,le,letwo) values(#{ename},#{job},7839,sysdate(),#{sal},0,10)")
    int  add(Map map);

    /**
     * 雇员更新
     * @param map
     * @return
     */
    @Update("update tb_emp set ename=#{ename},job=#{job},sal=#{sal},hiredate=sysdate() where empno=#{empno}")
    int update(Map map);

    /**
     * 雇员删除
     * @param empNo
     * @return
     */
    @Delete("delete from tb_emp where empno=#{empNo}")
    int delete(Integer empNo);
}
