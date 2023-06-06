package com.example.smartairportsystem.utils;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    private static final String USERNAME = "2238158271@qq.com";
    private static final String PASSWORD = "tqmrnourwpeodjch";

    private static HtmlEmail packUpEmail(){
        HtmlEmail mail = new HtmlEmail();
        /*发送邮件的服务器 126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com*/
        mail.setHostName("smtp.qq.com");
        /*不设置发送的消息有可能是乱码*/
        mail.setCharset("UTF-8");
        /*IMAP/SMTP服务的密码 username为你开启发送验证码功能的邮箱号 password为你在qq邮箱获取到的一串字符串*/
        mail.setAuthentication(USERNAME, PASSWORD);
        /*使用安全链接*/
        mail.setSSLOnConnect(true);
        return mail;
    }

    public static boolean isCorrect(String email){
        String[] spt = email.split("@");
        if(spt.length == 2){
            String[] spt1 = spt[1].split("\\.");
            String test = spt1[spt1.length-1];
            return test.equals("com") || test.equals("cn") || test.equals("org") || test.equals("gov");
        }else {
            return false;
        }
    }

    public static void sendAuthCodeEmail(String email,String code) {
        try {
            HtmlEmail mail = packUpEmail();
            /*发送邮件的邮箱和发件人*/
            mail.setFrom(USERNAME, "智慧机场系统");
            /*接收的邮箱*/
            mail.addTo(email);
            /*设置邮件的主题*/
            mail.setSubject("智慧机场系统验证码");
            /*设置邮件的内容*/
            mail.setMsg("尊敬的用户：您好！\n\t您的智慧机场系统账号"+email+"的验证码为：" + code + "\n（有效期2分钟）");
            mail.send();//发送
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendInformationEmail(String email,String content) {
        try {
            HtmlEmail mail = packUpEmail();
            /*发送邮件的邮箱和发件人*/
            mail.setFrom(USERNAME, "智慧机场系统");
            /*接收的邮箱*/
            mail.addTo(email);
            /*设置邮件的主题*/
            mail.setSubject("智慧机场系统消息通知");
            /*设置邮件的内容*/
            mail.setMsg(content);
            mail.send();//发送
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
