package com.xl.springboot.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * ftp通用上传下载方法
 */
@Component
public class xlFtpUtil {
    @Autowired
    private xlFtpConfig xlFtpConfig;

    private static FTPClient ftp = new FTPClient();
    /**
     * 将图片上传到ftp远程服务器
     */
    public  String  upLoad(MultipartFile multipartFile){
        // System.out.println(remoteIp+"...."+ftpUserName+","+ftpPassWord);
        //创建客户端对象

        InputStream inputStream = null;
        try {
            //  System.out.println(new FileUpAndDown().remoteIp);
            //连接ftp服务器
            ftp.connect(xlFtpConfig.getRemoteIp(),xlFtpConfig.getUploadPort());
            //登录
            ftp.login(xlFtpConfig.getFtpUserName(),xlFtpConfig.getFtpPassWord());
            //设置上传路径
            String path = xlFtpConfig.getRemotePath();
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if(!flag){
                //创建上传的路径 该方法只能创建一级目录,在这里如果/home/ftpadmin存在则可以创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型 二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            // MultipartFile multipartFile=null;
            //获取文件的绝对路径
            //String absolutePath = multipartFile.getResource().getFile().getAbsolutePath();
            //System.out.println(absolutePath+"绝对路径。。。。。。。。。。。");
            //获取上传原文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            //根据原文件的后缀，组装新文件名称，为了保证上传文件名称一样时被覆盖
            String newFileName= UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
            //根据上传文件在服务器上的位置实例化File对象
            File file =new File(xlFtpConfig.getLocalPath()+File.separator+newFileName);
            //调用springmvc封装的文件读写方法，上传文件
            multipartFile.transferTo(file);
            // org.apache.commons.io.FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
            //  System.out.println(file.length()+"............");
            inputStream = new FileInputStream(file);
            //第一个参数是文件名   ftp远程拷贝方法
            ftp.storeFile(file.getName(),inputStream);
            return newFileName;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                //关闭文件流
                if(inputStream!=null)
                    inputStream.close();
                //退出
                if(ftp!=null) {
                    //登出
                    ftp.logout();
                    //断开连接
                    ftp.disconnect();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * FTP文件下载
     * @param fileName
     */
    public   void downLoad(String fileName,HttpServletResponse response){
        InputStream local = null;
        OutputStream outputStream =null;
        try {
            //连接ftp服务器
            ftp.connect(xlFtpConfig.getRemoteIp(),xlFtpConfig.getUploadPort());
            //登录
            ftp.login(xlFtpConfig.getFtpUserName(),xlFtpConfig.getFtpPassWord());

            // System.out.println("开始下载文件");
            //initFtpClient();
            //切换FTP目录
            ftp.changeWorkingDirectory(xlFtpConfig.getRemotePath());
            //返回路径下的所有对象
            FTPFile[] ftpFiles = ftp.listFiles();
            //  ftpFiles  ftp 服务器上该目录下的所有文件 循环遍历
            for(FTPFile file : ftpFiles){
                //找到文件名称等于要下载的文件名称    equalsIgnoreCase  忽略大小写比较
                // "aaa".equals("AAA");   false
                //"aaa".equalsIgnoreCase("AAA");  true
                if(fileName.equalsIgnoreCase(file.getName())){
                    File localFile = new File(xlFtpConfig.getDownLoadPath() + "/" + fileName);
                    //os = response.getOutputStream();
                    outputStream =new FileOutputStream(localFile);
                    //从远程读写文件到本地
                    ftp.retrieveFile(file.getName(), outputStream);
                    //清空管道
                    outputStream.flush();
                    //下载
                    downloadFile(localFile,response);
                    // os.close();
                }
            }
            // ftpClient.logout();
            //System.out.println("下载文件成功");
        } catch (Exception e) {
            // System.out.println("下载文件失败");
            e.printStackTrace();
        } finally{

            try{
                if(ftp.isConnected()) {
                    ftp.disconnect();
                }
                if(null != outputStream) {
                    outputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 通用下载
     * @param file
     * @param response
     * @return
     */
    public static String downloadFile(File file, HttpServletResponse response) {
        // String fileName = "aim_test.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (file != null) {
            //设置文件路径   savePath ="d:/images/"   file ="d:/images/1.jpg"
            //  File file = new File(savePath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开 MIME
                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
              /* int i=0;
               while((i=bis.read(buffer))!=-1){
                   os.write(buffer, 0, i);
               }*/
                    // System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
