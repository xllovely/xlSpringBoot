package com.xl.springboot.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @className:PageUtil
 * @discription:
 * @author:小凌
 * @crateTime:2019/3/18 15:51
 */
public class PageUtil {
    //当前页
    private int pageNo;
    //每页显示数量
    private int pageSize;
    //总条数
    private int total;
    //请求的URL
    private String url;
    //动态拼接的字符串
    private String pageString;

    public PageUtil(int pageNo,int pageSize,int total,HttpServletRequest request){
        //给类属性赋值
        this.pageNo=pageNo;
        this.pageSize=pageSize;
        this.total=total;
        //获取URL
        url=request.getRequestURI()+"?";
        //动态获取所有请求地址携带的参数
        final Enumeration<String> parameterNames = request.getParameterNames();
        //动态封装
        while(parameterNames.hasMoreElements()){//判断有没有下一个参数
            String name=parameterNames.nextElement();
            //判断name值是否是pageNo,如果不是，开始拼接（pageNo需要每次都传递，不需要拼接）
            if(!"pageNo".equalsIgnoreCase(name)){
                if(url.charAt(url.length()-1)=='?'){
                    url+=name+"="+request.getParameter(name);
                }else{
                    url+="&"+name+"="+request.getParameter(name);
                }
            }
        }
        //判断循环后最后一位是否是？，如果不是，直接加上&
        if(url.charAt(url.length()-1)!='?'){
           url+="&";
        }
        //动态地获取URL并且拼装动态参数
    }

    /**
     * 获取分页字符串
     * @return
     */
    public String getPageString(){
        //首页 上一页 下一页 尾页 跳转到第几页 共多少条几页
        //计算最大页
        int maxPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        //拼接字符串，使用StringBuffer
        StringBuffer stringBuffer=new StringBuffer();
        if(pageNo<1){//当前页数不能小于1
             pageNo=1;
        }
        if(pageNo==1){//是首页
             stringBuffer.append("首页&nbsp;上一页&nbsp;");
        }else{
            stringBuffer.append("<a href='"+this.url+"pageNo=1'>首页</a>&nbsp;<a href='"+this.url+"pageNo="+(pageNo-1)+"'>上一页</a>&nbsp;");
        }
        if(pageNo>maxPage){//不能大于最大页
            pageNo=maxPage;
        }
        if(pageNo==maxPage){//是尾页
            stringBuffer.append("下一页&nbsp;尾页&nbsp;");
        }else{
            stringBuffer.append("<a href='"+this.url+"pageNo="+(pageNo+1)+"'>下一页</a>&nbsp;<a href='"+this.url+"pageNo="+maxPage+"'>尾页</a>&nbsp;");
        }
        stringBuffer.append("跳转到<select onchange=\"javascript:location.href='"+this.url+"pageNo='+this.value\">");
        for (int i=1;i<=maxPage;i++){
            if(pageNo==i){//当前选中页等于跳到的页数时，让选中
                stringBuffer.append("<option selected='selected'>"+i+"</option>");
            }else{
                stringBuffer.append("<option>"+i+"</option>");
            }

        }
        stringBuffer.append("跳转到</select>&nbsp;共"+total+"条&nbsp;共"+maxPage+"页");
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
       String s="/sbd/dept/list?aaa=111";
       System.out.println(s.charAt(s.length()-1)=='?');
    }
}
