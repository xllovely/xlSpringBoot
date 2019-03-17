package com.xl.springboot.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 去配置文件取值
 */
@Component
@ConfigurationProperties(prefix = "ftp")
@PropertySource("classpath:xlftp.properties")
public class xlFtpConfig {
    private String  remoteIp;
    private int uploadPort;
    private String ftpUserName;
    private String ftpPassWord;
    private String remotePath;
    private String localPath;
    private String downLoadPath;

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public int getUploadPort() {
        return uploadPort;
    }

    public void setUploadPort(int uploadPort) {
        this.uploadPort = uploadPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpPassWord() {
        return ftpPassWord;
    }

    public void setFtpPassWord(String ftpPassWord) {
        this.ftpPassWord = ftpPassWord;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getDownLoadPath() {
        return downLoadPath;
    }

    public void setDownLoadPath(String downLoadPath) {
        this.downLoadPath = downLoadPath;
    }

    /**
     * 读写配置文件
     * @param args
     */
/*    public static void main(String[] args) {
        Properties properties=new Properties();
        InputStream inputStream=xlFtpConfig.class.getResourceAsStream("/xlftp.properties");
        try{
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        //System.out.println(properties.get(ftp.uploadPort));
        Set<Object> keys=properties.keySet();
        *//*for (Object key:
             keys) {
            System.out.println("键："+key+"值"+properties.get(key));
        }*//*
        Iterator<Object> iterator=keys.iterator();
        while (iterator.hasNext()){
            System.out.println("键："+iterator.next()+"值"+properties.get(iterator.next()));
        }
    }*/
}
