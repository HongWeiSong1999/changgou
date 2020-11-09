package com.hws.util;

import com.hws.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName:FastDFSUtil
 * Package:com.hws
 * Description:
 *
 * @ date:2020/10/13 16:41
 * @ author:hws
 */
public class FastDFSUtil {

    /**
     * 加载Tracker链接信息
     */
    static {
        try {
            //查找classpath下面的路径
            String path = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void getStorageClient() throws IOException {
        //1、创建一个Tracker访问的客户端对象TrackerClient()
        TrackerClient trackerClient = new TrackerClient();

        //2、通过TrackerClient访问TrackerService服务,获取链接信息
        TrackerServer trackerServer = trackerClient.getConnection();
    }

    /**
     * @Description: 文件上传
     * @Author hws
     * @Date 21:17 2020/10/13
     * @Param
     **/
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{

        //0、附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author",fastDFSFile.getAuthor());

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String[] uploadFile = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);

        //3、通过链接信息获取StorageClient对象存储的信息
        //StorageClient storageClient = new StorageClient(trackerServer,null);

        /**
         * 通过storageClient访问Storage,实现文件上传
         * 1、文件的字节数组
         * 2、文件的扩展名
         * 3、附加参数
         */
        //String[] uploadFile = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        //return uploadFile;
        return uploadFile;
    }


    /**
     * @Description: //文件信息的获取
     * @Author hws
     * @Date 12:07 2020/10/14
     * @Param [groupName,  代表的是组名 如group1
     *         remoteFilename]   代表的是存放路径名  M00/00/00/wKh9tF-GZpeAWvJ0ABsZBUKaffE577.png
     **/
    public static FileInfo getFile(String groupName, String remoteFilename) throws IOException, MyException {
        //1、创建与Tracker的链接
        TrackerClient trackerClient = new TrackerClient();
        //2、创建一个Tracker访问的客户端对象TrackerClient()
        TrackerServer trackerServer = trackerClient.getConnection();
        //3、通过链接信息获取StorageClient对象的存储信息
        StorageClient storageClient = new StorageClient(trackerServer,null);
        //4、获取文件信息
        return storageClient.get_file_info(groupName, remoteFilename);
    }

    /**
     * @Descr iption: 文件下载
     * @Author hws
     * @Date 13:30 2020/10/14
     * @Param [groupName, remoteFilename]
     **/
    public static InputStream downloadFile(String groupName, String remoteFilename) throws IOException, MyException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer,null);
        byte[] bytes = storageClient.download_file(groupName, remoteFilename);
        //字节变成InputStream返回回去
        return new ByteArrayInputStream(bytes);
    }

    /**
     * @Description: 删除文件
     * @Author hws
     * @Date 14:30 2020/10/14
     * @Param [groupName, remoteFilename]
     **/
    public static Integer deleteFile(String groupName, String remoteFilename) throws IOException, MyException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer,null);
        return storageClient.delete_file(groupName, remoteFilename);
    }


    /**
     * @Description: 获取storage组的信息
     * @Author hws
     * @Date 14:37 2020/10/14
     * @Param [groupName]
     **/
    public static StorageServer getStorage(String groupName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer,groupName);
    }

    /**
     * @Description: 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @Author hws
     * @Date 18:11 2020/10/14
     * @Param [groupName, remoteFileName]
     *
     * @return*/
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
    }

    /**
     * @Description: 获取Tracker服务地址
     * @Author hws
     * @Date 18:30 2020/10/14
     * @Param []
     **/
    public static String getTrackerUrl() throws IOException {
         TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        String hostString = trackerServer.getInetSocketAddress().getHostString();
        int port = ClientGlobal.getG_tracker_http_port();
        String url = hostString + ":" + port;
        return url;
    }

    public static void main(String[] args) throws Exception {
        /*文件信息获取
        FileInfo fileInfo = getFile("group1", "M00/00/00/wKh9tF-GZpeAWvJ0ABsZBUKaffE577.png");
        System.out.println(fileInfo.getFileSize());
        System.out.println(fileInfo.getSourceIpAddr());*/

        /*//文件下载
        InputStream is = downloadFile("group1", "M00/00/00/wKh9tF-GZpeAWvJ0ABsZBUKaffE577.png");
        //将文件写入磁盘中
        FileOutputStream os = new FileOutputStream("G:\\畅购商城\\2.畅购商城\\02-分布式文件存储FastDFS\\讲义\\1.png");
        //定义一个缓冲区
        byte[] buffer = new byte[1024];
        while (is.read(buffer) != -1){
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();*/

        /*删除信息
        deleteFile("group1", "M00/00/00/wKh9tF-GZpeAWvJ0ABsZBUKaffE577.png");*/

        /*StorageServer storageServer = getStorage("group1");
        System.out.println(storageServer.getStorePathIndex()); //获取下标
        System.out.println(storageServer.getInetSocketAddress().getHostString()); //获取ip地址*/

        /*server信息
        ServerInfo[] serverInfo = getServerInfo("group1", "M00/00/00/wKh9tF-G0SiAHZoCAAAbVQ3fyvY506.png");
        System.out.println(serverInfo[0].getIpAddr());
        System.out.println(serverInfo[0].getPort());*/

        System.out.println(getTrackerUrl());

    }

}
