package com.sm.my.shop.two.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 异步上传文件
 */
@Controller
public class UploadController {
    public static final String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> load(MultipartFile dropFile, MultipartFile editFile, HttpServletRequest request) {
        MultipartFile myFile = dropFile == null ? editFile : dropFile;
        Map<String, Object> result = new HashMap<>();

//        获取文件名称
        String fileRealName = myFile.getOriginalFilename();
        String fileSuffix = fileRealName.substring(fileRealName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + fileSuffix;
        //        获取根目录真实路径
//        request.getSession().getServletContext().getRealPath("/");
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
//        判断是否存在文件夹，没有则创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        写入文件
        file = new File(filePath, fileName);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        返回数据
        if (dropFile != null) {
            result.put("fileName", UPLOAD_PATH + fileName);
        } else {
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            result.put("errno", 0);
            result.put("data", new String[]{
                    serverPath + UPLOAD_PATH + fileName
            });
        }
        return result;
    }
}
