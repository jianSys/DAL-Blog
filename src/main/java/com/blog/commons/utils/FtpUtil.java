package com.blog.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
/**
 * @program: dal-blog
 * @description: ftp操作工具类
 * @author: jian
 * @create: 2021-06-30 23:25
 **/
public class FtpUtil {

    /** ftp服务器地址 */
    private String host;
    /** ftp 端口号 默认21 */
    private int port = 21;
    /** ftp服务器用户名 */
    private String username;
    /** ftp服务器密码 */
    private String password;
    /** ftp远程目录 */
    private String remoteDir;
    /** 本地存储目录 */
    private String localDir;
    /** 文件路径通配符 默认列出所有*/
    private String regEx = "*";
    /** 指定要下载的文件名 */
    private String downloadFileName;

    /*
     *@Description: 获取ftp连接
     *@param host        服务器地址
     *@param port        端口号
     *@param username    用户名
     *@param password    密码
     *@return:           ftp连接
     *@Author:  William
     *@Date:  2019/5/20 10:21
     */
    public static FTPClient getConnect(String host, int port, String username, String password){

        FTPClient ftp = new FTPClient();
        int reply;
        try {
            ftp.connect(host,port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("用户名密码错误");
                ftp.disconnect();
                return null;
            }
        } catch (IOException e) {
            System.out.println("端口错误");
            e.printStackTrace();
        }
        return ftp;
    }

    /*
     *@Description: 断开ftp连接
     *@param ftp连接
     *@Author:  William
     *@Date:  2019/5/20 10:39
     */
    public static void disConnect(FTPClient ftp){
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException ioe) {
            }
        }
    }

    /* Description: 向FTP服务器上传文件
     * @param host FTP服务器ip
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录,/home/ftpuser/images
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2018/05/28。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = getConnect(host,port,username,password);
        try {
            if(ftp == null){
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置为被动模式
            ftp.enterLocalPassiveMode();
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect(ftp);
        }
        return result;
    }


    /**
     * Description: 从FTP服务器下载文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath, String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = getConnect(host,port,username,password);
        try {
            if(ftp == null){
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            try {
                FTPFile[] fs = ftp.listFiles();
                for (FTPFile ff : fs) {
                    if (ff.getName().equals(fileName)) {
                        File localFile = new File(localPath + "/" + ff.getName());
                        OutputStream is = new FileOutputStream(localFile);
                        ftp.retrieveFile(ff.getName(), is);
                        is.close();
                    }
                }
                ftp.logout();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect(ftp);
        }
        return result;
    }

    /*
     *@Description: 删除文件夹中的文件
     *@param remotePath 要删除的文件夹  /home/ftpuser/www/img/teacherQualification/2015/01/22
     *@return:
     *@Author:  William
     *@Date:  2019/5/20 13:29
     */
    public static boolean delFolder(String host, int port, String username, String password,String uploadPath, String remotePath){
        boolean result = false;
        if(!remotePath.contains(uploadPath)) {
            remotePath = uploadPath+"/"+remotePath ;
        }
        FTPClient ftp = getConnect(host,port,username,password);
        try {
            if(ftp == null){
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                //循环删除文件
                delFile(host,port,username,password,remotePath+"/"+ff.getName());
            }
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect(ftp);
        }
        return result;
    }

    /*
     *@Description: 删除文件
     *@param filePath 删除文件的物理路径  /usr/local/www/img/....
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     *@return:
     *@Author:  William
     *@Date:  2019/5/20 10:51
     */
    public static boolean delFile(String host, int port, String username, String password, String filePath){
        boolean result = false;
        FTPClient ftp = getConnect(host,port,username,password);
        if(ftp == null || StringUtils.isEmpty(filePath) ){
            return result;
        }
        try {
            ftp.deleteFile(filePath);
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            disConnect(ftp);
        }
        return result;
    }


    /*
     *@Description: 重命名文件（原路径和新路径必须在同一个文件夹）
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     *@param filePath  文件路径
     *@param newPath   文件新路径
     *@return:
     *@Author:  William
     *@Date:  2019/5/20 11:16
     */
    public static boolean changeFileName(String host, int port, String username, String password, String filePath,String newPath){
        boolean result = false;
        FTPClient ftp = getConnect(host,port,username,password);
        if(ftp == null || StringUtils.isEmpty(filePath) ){
            return result;
        }
        try {
            ftp.changeWorkingDirectory(filePath.substring(0,filePath.lastIndexOf("/")));
            ftp.rename(filePath,newPath);
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            disConnect(ftp);
        }
        return result;
    }


    /**
     * @param host
     * @param port
     * @param username
     * @param password
     * @param filePath 原路径/home/ftpuser/www/img/teacherQualification/lesosnImg/106/lessonDetail/temp/0750bd4c9d91906418db886b2e659fe.jpg
     * @param newPath  目标路径/home/ftpuser/www/img/teacherQualification/lesosnImg/106/lessonDetail/img/0750bd4c9d91906418db886b2e659fe.jpg
     * @return boolean
     * @author: lijian
     * @description: 移动文件
     * @date 2019/5/20 13:41
     */
    public static boolean changeFilePath(String host, int port, String username, String password, String filePath,String newPath){
        boolean result = false;
        FTPClient ftp = getConnect(host,port,username,password);
        if(ftp == null || StringUtils.isEmpty(filePath) ){
            return result;
        }
        try {
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            InputStream inputStream = ftp.retrieveFileStream(new String(filePath.getBytes("UTF-8"), "ISO-8859-1"));
            //获取上传文件的文件名
            String uploadFileName = newPath.substring(newPath.lastIndexOf("/")+1,newPath.length());
            //获取上传路径
            String uploadPath  = newPath.substring(0,newPath.lastIndexOf("/"));
            String fileTempPath = uploadPath.substring(uploadPath.lastIndexOf("img/")+4,uploadPath.length());
            uploadFile(host, port, username, password,"/home/ftpuser/www/img",fileTempPath,uploadFileName,inputStream);
            ftp.deleteFile(filePath);
            inputStream.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            disConnect(ftp);
        }
        return result;
    }


    public static void main(String[] args) {
        FTPClient connect = getConnect("49.232.1.128", 21, "root", "123456");
        System.out.println(connect.toString());
    }
}
