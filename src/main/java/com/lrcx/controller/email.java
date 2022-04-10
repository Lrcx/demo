package com.lrcx.controller;

import com.alibaba.fastjson.JSON;

import com.lrcx.pojo.UserEmail;
import com.lrcx.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Controller
public class email {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    Random random;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserEmail aemail;

    @PostMapping("/send_email")
    @ResponseBody
    public Object send_email(HttpServletRequest request){
        String user_email=request.getParameter("email");
        System.out.println("email================"+user_email);
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
        String text=random.random();
        System.out.println(text);
        redisTemplate.opsForValue().set("yzm",text,300, TimeUnit.SECONDS);
        System.out.println("redis=========="+redisTemplate.opsForValue().get("yzm"));
        simpleMailMessage.setSubject("xxx系统邮箱验证码");
        simpleMailMessage.setText("您的验证码是："+text+",此验证码五分钟内有效");
        simpleMailMessage.setTo(user_email);
        simpleMailMessage.setFrom("1016312264@qq.com");
        mailSender.send(simpleMailMessage);
        aemail.setUser_email(user_email);
        aemail.setYzm(text);
        return aemail;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
