package com.sm.my.shop.two.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 异步上传文件
 */
@Controller
public class UploadController {
    public static final String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> load(MultipartFile dropFile, MultipartFile[] editFiles, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

//        dropFile上传
        if(dropFile!=null){
            result.put("fileName", writeFile(dropFile,request));
        }
        if(editFiles!=null && editFiles.length>0) {
//            wangEditor上传
            List<String> fileNames = new ArrayList<>();
            for (MultipartFile editFile : editFiles) {
                fileNames.add(writeFile(editFile,request));
            }
            result.put("errno", 0);
            result.put("data", fileNames);
        }
        return result;
    }

    /**
     * 存储文件并返回文件在服务器中的地址
     * @param multipartFile
     * @param request
     * @return
     */
    public String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
//        获取文件名
        String fileRealName = multipartFile.getOriginalFilename();
        String fileSuffix = fileRealName.substring(fileRealName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + fileSuffix;
//        获取文件路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
//        判断是否存在文件夹，没有则创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        写入文件
        file = new File(filePath, fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        完整的文件路径
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return serverPath + UPLOAD_PATH + fileName;
    }
}
