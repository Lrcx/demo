package com.lrcx.controller;

import com.alibaba.fastjson.JSON;
import com.lrcx.mapper.ResumeMapper;
import com.lrcx.pojo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private ResumeMapper resumeMapper;

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/getallresume")
    public String getallresume(){
        List<Resume> resume=resumeMapper.queryAllResume();
        return JSON.toJSONString(resume);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(request.getParameter("name"));
        Resume resume=new Resume(request.getParameter("name"),file.getInputStream());
        resumeMapper.insertResume(resume);
        List<Resume> resaume=resumeMapper.queryAllResume();
        return JSON.toJSONString(resaume);
    }

    @GetMapping("/edit")
    @ResponseBody
    public void edit(HttpServletRequest request){
        String oldname=request.getParameter("oldtitle");
        String newname=request.getParameter("name");
        resumeMapper.editResume(oldname,newname);
    }

    @GetMapping("/preview/{name}")
    public void pdfStreamHandler(@PathVariable("name") String name, HttpServletResponse response){
        System.out.println("======================preview========================"+name);
        Resume resume=resumeMapper.queryResume(name);
        byte[] data=null;
        InputStream input=null;
        try{

            input=resume.getFile();
            data=new byte[input.available()];
            input.read(data);
            response.getOutputStream().write(data);
        }catch (Exception e){
            System.out.printf("pdf文件处理异常"+e);
        }finally {
            try{
                if(input!=null){
                    input.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/delresume")
    @ResponseBody
    public void delresume(@RequestParam("name") String name){
        System.out.println(name);
        resumeMapper.deleteResume(name);
    }
}
