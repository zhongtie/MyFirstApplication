package com.installman.zhong.myfirstapplication;

/**
 * Created by zhong on 17-2-20.
 */

public class NewMail implements Runnable {
    private static final String mSender = "mylbstestcc@163.com";//这里以163邮箱为例，根据变量名能看出来这个变量是发送者的邮箱地址
    private static final String mSenderPass = "cctsetsblym1983";//发送者的邮箱密码
    private static final String HostName = "smtp.163.com";//163邮箱的发送邮件服务器
    private static final String HostPort = "465";//服务器端口
    private static final String mReceiver = "zhongtie913@163.com";//接收者邮箱

    private String mTitle,mMsg;//邮件的标题和内容
    public NewMail(String title, String msg) {
        mTitle=title;
        mMsg=msg;
    }
    @Override
    public void run() {
        MailUtils sender = new MailUtils().setUser(mSender).setPass(mSenderPass)
                .setFrom(mSender).setTo(mReceiver).setHost(HostName)
                .setPort(HostPort).setSubject(mTitle).setBody(mMsg);
        sender.init();
        try {
            //sender.addAttachment(FilePath,FileName);//添加附件
            sender.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
