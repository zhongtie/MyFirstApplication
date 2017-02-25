package com.installman.zhong.myfirstapplication;

import android.util.Log;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by zhong on 17-2-24.
 */

public class MailUtils {
    private String TAG = "MailUtils";
    private static final String mSender = "mylbstestcc@163.com";//这里以163邮箱为例，根据变量名能看出来这个变量是发送者的邮箱地址
    private static final String mMailAccName = "mylbstestcc";//发送者的邮箱帐号
    private static final String mMailAccPass = "cctsetsblym0401";//发送者的邮箱授权密码
    private static final String HostName = "smtp.163.com";//163邮箱的发送邮件服务器
    private static final String mReceiver = mSender;//接收者邮箱
    private String mTitle,mMsg;//邮件的标题和内容

    public MailUtils(String title, String msg){
        mTitle = title;
        mMsg = msg;
    }

    public MailUtils(){

    }

    public String getTitle(){
        StringBuffer title = new StringBuffer();
        try{
            Properties props = new Properties();
            //存储接收邮件服务器使用的协议，这里以POP3为例
            props.setProperty("mail.store.protocol", "pop3");
            //设置接收邮件服务器的地址，这里还是以网易163为例
            props.setProperty("mail.pop3.host", "pop3.163.com");
            //根据属性新建一个邮件会话.
            Session session=Session.getInstance(props);
            //从会话对象中获得POP3协议的Store对象
            Store store = session.getStore("pop3");
            //如果需要查看接收邮件的详细信息，需要设置Debug标志
            session.setDebug(true);

            //连接邮件服务器
            store.connect("pop3.163.com", mMailAccName, mMailAccPass);

            //获取邮件服务器的收件箱
            Folder folder = store.getFolder("INBOX");
            //以只读权限打开收件箱
            folder.open(Folder.READ_ONLY);
            //获取收件箱中的邮件，也可以使用getMessage(int 邮件的编号)来获取具体某一封邮件
            Message message[] = folder.getMessages();
            for (int i=0, n=message.length; i<n; i++) {
                //test
                Log.v(TAG, message[i].getSubject() + message[i].getSentDate() + message[i].getMessageNumber());
                title.append(message[i].getSubject());
            }
            //关闭连接
            folder.close(false);
            store.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return title.toString();
    }

    public void sendMail(){
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
            transport.connect(mMailAccName, mMailAccPass);
            // 发送邮件，自己发给自己
            transport.sendMessage(msg, new Address[]{new InternetAddress(mReceiver)});
            // 关闭连接
            transport.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
