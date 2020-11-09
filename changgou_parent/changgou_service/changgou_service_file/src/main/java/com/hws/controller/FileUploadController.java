package com.hws.controller;

import com.hws.file.FastDFSFile;
import com.hws.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:FileUploadController
 * Package:com.hws.controller
 * Description:
 *
 * @ date:2020/10/14 10:08
 * @ author:hws
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    /**
     * @Description: 上传文件
     * @Author hws
     * @Date 10:41 2020/10/14
     * @Param [file]
     **/
    @PostMapping
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),   //文件名字
                file.getBytes(), //获取文件的字节
                StringUtils.getFilenameExtension(file.getOriginalFilename()) //获取文件的扩展名
        );
        String[] upload = FastDFSUtil.upload(fastDFSFile);
        String url = FastDFSUtil.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];
        return new Result(true, StatusCode.OK, "上传成功",url);
    }
}
