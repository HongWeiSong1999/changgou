package com.hws.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

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

}
