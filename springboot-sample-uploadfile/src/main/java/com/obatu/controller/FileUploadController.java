package com.obatu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by yunfeng.liu on 2017/4/12.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = {"/", "/index"})
    @ResponseBody
    public Object index() {
        return new ModelAndView("redirect:/file/upload");
    }

    /**
     * 单文件上传表单页面
     *
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.GET)
    public String fileUpload() {
        return "/fileupload";
    }

    /**
     * 多文件上传表单页面
     *
     * @return
     */
    @RequestMapping(value = "/file/upload/multiple", method = RequestMethod.GET)
    public String multipleFileUpload() {
        return "/multipleFileUpload";
    }

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request, MultipartFile file) {
        if (file != null) {
            String uploadDir = request.getServletContext().getRealPath("/upload/dest");
            File destFile = new File(uploadDir, file.getOriginalFilename());
            try {
                if (!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
                file.transferTo(destFile);
                return "upload success: " + destFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "upload failure";
        }
        return "please select file to upload.";
    }

    @RequestMapping(value = "/file/upload/multiple", method = RequestMethod.POST)
    @ResponseBody
    public String handleMultipleFileUpload(HttpServletRequest request, @RequestParam(name = "file") MultipartFile[] files) {
        if (files != null && files.length > 0) {
            File uploadDir = new File(request.getServletContext().getRealPath("/upload/dest"));
            try {
                if (!uploadDir.exists()) uploadDir.mkdirs();
                StringBuilder sb = new StringBuilder("upload files: <br>");
                for (MultipartFile file : files) {
                    File destFile = new File(uploadDir, file.getOriginalFilename());
                    file.transferTo(destFile);
                    sb.append(destFile.getAbsolutePath()).append("<br>");
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "upload failure";
        }
        return "please select files to upload.";
    }
}
