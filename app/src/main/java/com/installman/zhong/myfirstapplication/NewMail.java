package com.installman.zhong.myfirstapplication;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by zhong on 17-2-20.
 */

public class NewMail implements Runnable {
    private static final String mSender = "mylbstestcc@163.com";//这里以163邮箱为例，根据变量名能看出来这个变量是发送者的邮箱地址
    private static final String mSenderName = "mylbstestcc";//发送者的邮箱帐号
    private static final String mSenderPass = "cctsetsblym0401";//发送者的邮箱授权密码
    private static final String HostName = "smtp.163.com";//163邮箱的发送邮件服务器
    private static final String mReceiver = "mylbstestcc@163.com";//接收者邮箱

    private String mTitle,mMsg;//邮件的标题和内容
    public NewMail(String title, String msg) {
        mTitle=title;
        mMsg=msg;
    }
    @Override
    public void run() {
        try {
            Properties props = new Properties();
            // 开启debug调试
            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", HostName);
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");

            // 设置环境信息
            Session session = Session.getInstance(props);

            // 创建邮件对象
            Message msg = new MimeMessage(session);
            msg.setSubject(mTitle);
            // 设置邮件内容
            msg.setText(mMsg);
            // 设置发件人
            msg.setFrom(new InternetAddress(mSender));

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect(mSenderName, mSenderPass);
            // 发送邮件，自己发给自己
            transport.sendMessage(msg, new Address[]{new InternetAddress(mReceiver)});
            // 关闭连接
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
